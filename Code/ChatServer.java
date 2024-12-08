package Lab2;
import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
  String displayText="";
  public String handleRequest(URI url){
    String query = url.getQuery();
    if(url.getPath().contains("/add-message")) {
      if(query.startsWith("s=")&&query.contains("user=")) {
          String message = query.split("&")[0].split("=")[1];
          String user = query.split("=")[2];
          displayText+= String.format("%s : %s\n", user, message);
          return displayText;
      }else{
        return "/add requires query parameter s and user, separate in the middle by &.\n";
      }
    }else {
      return "Request in the format of: /add-message?s=<string>&user=<string> ";
    }
  }

}

class ChatServer {
  public static void main(String[] args) throws IOException {
    if(args.length == 0){
      System.out.println("Missing port number! Try any number between 1024 to 49151");
      return;
  }
    int port = Integer.parseInt(args[0]);

    Server.start(port, new Handler());
  }
}
