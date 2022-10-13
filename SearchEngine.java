import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;


class SearchEngineMaker implements URLHandler
{
    ArrayList<String> added = new ArrayList<>();
    ArrayList<String> result= new ArrayList<>();
    public String handleRequest (URI url)
    {
        
        if (url.getPath().contains("/add")) 
        {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s"))
            {
            added.add(parameters[1]);
            return String.format("%b has been added!", parameters[1]);
            }
        }
        
        if (url.getPath().contains("/search")){
            String[] parameters = url.getQuery().split("=");
            
            if (parameters[0].equals("s"))
            {
                for (int i = 0; i < added.size(); i++)
                {
                    if (added.get(i).contains(parameters[1]))
                    {
                        result.add(added.get(i));
                    }
                }
                return result.toString();
            }

            
        }

        return null;

    }
}



class SearchEngine
{
    public static void main(String[] args) throws IOException{
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new SearchEngineMaker());
    }
}