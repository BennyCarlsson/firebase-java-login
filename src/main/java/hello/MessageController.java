package hello;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.tasks.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class MessageController {

    @GetMapping("/")
    public String checkIfLoggedIn(Model model, @RequestParam(value = "idToken", required = false) String idToken){
        if(checkIdToken(idToken)){
            return "index";
        }
        return "login";
    }
    private boolean checkIdToken(String idToken){
        if(idToken == null){
            return false;
        }
        Task<FirebaseToken> authTask = FirebaseAuth.getInstance().verifyIdToken(idToken);
        try {Tasks.await(authTask);}
        catch(ExecutionException | InterruptedException e ){ System.out.print(e);}
        return authTask.isSuccessful();
    }
}
