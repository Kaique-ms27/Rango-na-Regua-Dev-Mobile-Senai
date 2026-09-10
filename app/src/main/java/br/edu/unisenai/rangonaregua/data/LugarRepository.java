package br.edu.unisenai.rangonaregua.data;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import br.edu.unisenai.rangonaregua.model.Lugar;

public class LugarRepository {

    // CREATE
    public Task<DocumentReference> inserir(Lugar lugar){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db.collection("lugares").add(lugar);
    }

    // READ
    public ListenerRegistration lerRealTime(EventListener<QuerySnapshot>callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db.collection("lugares")
                .orderBy("votos", Query.Direction.DESCENDING)
                .addSnapshotListener(callback);
    }

    // UPDATE
    public Task<Void> votar (Lugar lugar){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db.collection("lugares")
                .document(lugar.getId())
                .update("votos", FieldValue.increment(1));
    }

    // DELETE
    public Task<Void> excluir(Lugar lugar) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db.collection("lugares")
                .document(lugar.getId())
                .delete();
    }

    // RESTAURAR ITEM RECÉM APAGADO
    public Task<Void> restaurar (Lugar lugar){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db.collection("lugares")
                .document(lugar.getId())
                .set(lugar);
    }

}
