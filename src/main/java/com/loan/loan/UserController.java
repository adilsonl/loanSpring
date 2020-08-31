package com.loan.loan;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import models.User;

@RestController
public class UserController {

	public static final String COL_NAME = "users";

	@PostMapping("/addUser")
	public String addUser(@RequestBody User user) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(user.getCpf())
				.set(user);
		return collectionsApiFuture.get().getUpdateTime().toString();
	}

	@GetMapping("/getUser")
	public User getUser(@RequestParam String cpf) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(cpf);
		ApiFuture<DocumentSnapshot> future = documentReference.get();

		DocumentSnapshot document = future.get();

		User user = new User();

		if (document.exists()) {
			user = document.toObject(User.class);
		} 
		
		return user;
	}

	@GetMapping("/getAll")
	public ArrayList<User> getAllUsers() throws InterruptedException, ExecutionException {
		ArrayList<User> users = new ArrayList<User>();
		ApiFuture<QuerySnapshot> query = FirestoreClient.getFirestore().collection(COL_NAME).get();
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documents) {
			users.add(document.toObject(User.class));
		}
		return users;
	}

	@PutMapping("/updateUser")
	public String updateUser(@RequestBody User user) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(user.getCpf())
				.set(user);
		return collectionsApiFuture.get().getUpdateTime().toString();
	}

	@DeleteMapping("/deleteUser")
	public String deleteUser(@RequestParam String cpf) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(cpf).delete();
		return "Document with User ID " + cpf + " has been deleted";
	}

	@PostMapping("/loginUser")
	public User loginUser(@RequestBody ObjectNode json) throws InterruptedException, ExecutionException {
		String email = json.get("email").asText();
		String senha = json.get("password").asText();
		User response = new User();
		ApiFuture<QuerySnapshot> query = FirestoreClient.getFirestore().collection(COL_NAME).get();
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documents) {
			User user = document.toObject(User.class);
			if (user.getEmail().equals(email) && user.getPassword().equals(senha)) {
				response = document.toObject(User.class);
			}
		}
		return response;
	}

}
