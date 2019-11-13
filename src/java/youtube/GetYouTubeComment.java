package youtube;


public class GetYouTubeComment {
    private String clientID;

    public GetYouTubeComment(String clientID) {
        this.clientID = clientID;
    }

    public String sendMessage(String email, String pass) {
        try {

            String client_key = "668126815086-4fr2cpip96pgbopv4snt199okjrnb88j.apps.googleusercontent.com";
            String dev_key = "AIzaSyCWuYRVnpWLB-eMjfdWft9q11iDoALwb7Q";
        } catch (Exception e) {
            System.out.println("Exception:" + e);
            e.printStackTrace();
        }
        return "";
    }

}