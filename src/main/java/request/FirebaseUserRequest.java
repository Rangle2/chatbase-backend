package request;

import lombok.Data;

@Data
public class FirebaseUserRequest {
    private String firebaseUid;
    private String displayName;
    private String email;
}
