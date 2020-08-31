package com.loan.loan;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import models.Loan;
import models.User;

@RestController
public class LoanController {

	public static final String COL_NAME = "loans";

	@PostMapping("/addLoan")
	public String addLoan(@RequestBody Loan loan) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<DocumentReference> documentApiFuture = dbFirestore.collection(COL_NAME).add(loan.toMap());
		return documentApiFuture.get().getId();
	}

	@GetMapping("/getLoan")
	public Loan getLoan(@RequestParam String hashID) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(hashID);
		ApiFuture<DocumentSnapshot> future = documentReference.get();

		DocumentSnapshot document = future.get();

		Loan loan = new Loan();

		if (document.exists()) {
			loan = document.toObject(Loan.class);
		}
		return loan;
	}

	@DeleteMapping("/deleteLoan")
	public String deleteLoan(@RequestParam String hashID) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(hashID).delete();
		return "Document with User ID " + hashID + " has been deleted";
	}

	@PutMapping("/updateLoan")
	public String updateLoan(@RequestBody Loan loan) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(loan.getHashID())
				.set(loan);
		return collectionsApiFuture.get().getUpdateTime().toString();
	}

	@GetMapping("/getAllLoans")
	public ArrayList<Loan> getAllLoans(@RequestParam String cpfUser) throws InterruptedException, ExecutionException {
		ArrayList<Loan> loans = new ArrayList<Loan>();
		ApiFuture<QuerySnapshot> query = FirestoreClient.getFirestore().collection(COL_NAME).get();
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documents) {
			Loan loan = document.toObject(Loan.class);
			if(loan.getCpfUser().equals(cpfUser))
				loans.add(loan);
		}
		return loans;
	}
}
