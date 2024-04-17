import java.io.*;
public class NameList{
    public static void main(String [] args) throws IOException
    {
       //File F = new File(args[0]);
       NameList n = new NameList();
       //BufferedReader in = new BufferedReader ( new FileReader(F) );
       String input;
       
       //while ( (input = in.readLine() ) != null)
       BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
       while ( (input =in.readLine()) != null)
       {
        switch ( input.charAt(0) )
        {
            case 'A':
                n.add( input.substring(2) );
                break;
            case 'R':
                if (input.substring(2).length()==1)
                {
                    n.removeLetter( input.substring(2));
                }
                else
                {
                    n.remove( input.substring(2) );
                }
                
                break;
            case 'C':
                if (n.find( input.substring(2) ))
                {
                    System.out.println("Found");
                }
                else
                {
                    System.out.println("Not Found");
                }
                break;
            case 'S':
                System.out.println( n.toString() );
                break;
            default:
                System.out.println("Incorrect input");
                break;
        }
        //System.out.println("_____");
        //n.print();
        //System.out.println("_____");
       }
       in.close();
    }
    
        Node head;
        NameList()
        {
            head = null;
        }
        public void add (String a)
        {
            if (a.length()<2)
            {
                System.out.println("Input too short");
                return;
            }

            Node newNode = new Node (a);
            if (head == null)
            {
                Node letter = new Node (a.substring(0, 1).toUpperCase());
                head = letter;
                head.next = newNode;
                //add(a);
            }
            else
            {
                Node itter = head;
                while (itter!= null)
                {
                    if (itter.next == null)
                    {
                        Node letter = new Node (a.substring(0, 1).toUpperCase());
                        itter.next = letter;
                        itter.next.next = newNode;
                        return;
                    }
                    else if (itter.next.get().toUpperCase().charAt(0) == a.toUpperCase().charAt(0))
                    {
                        //itter = itter.next;
                        while (itter.next.get().toLowerCase().compareTo(a.toLowerCase()) < 0)
                        {
                            if (itter.next.next == null)
                            {
                                itter=itter.next;
                                break;
                            }
                            itter = itter.next;
                        }
                        newNode.next = itter.next;
                        itter.next = newNode;

                        return;
                    }
                    else if ( itter.next.get().toUpperCase().charAt(0) > a.toUpperCase().charAt(0))
                    {
                        if (itter == head)
                        {
                            Node letter = new Node (a.substring(0, 1).toUpperCase());
                            letter.next = newNode;
                            newNode.next = head;
                            head = letter;
                            break;
                        }
                        Node letter = new Node (a.substring(0, 1).toUpperCase());
                        newNode.next = itter.next;
                        letter.next = newNode;
                        itter.next = letter;
                        
                        
                        return;
                    }

                    itter = itter.next;
                }
                
            }
        }
        public String toString()
        {
            String output ="";
            Node itter = head;
            while(itter != null)
            {
                if (itter ==  head)
                {
                    output += itter.get() ;
                }
                else if (itter.get().length() == 1)
                {
                    output += '\n' +itter.get() ;
                }
                else
                {
                    output += "\n  " + itter.get();
                }
                
                itter = itter.next;
            }
            return output;
        }
        public void remove(String a)
        {
            if (head == null)
            {
                System.out.println("empty");
                System.exit(0);
                return;
            }

            Node itter = head;
            if(head.get().compareTo(a)==0)
            {
                head = itter.next;
                cleanUp();
            }
            while (itter.next != null)
            {
                if (itter.next.get().compareTo(a) == 0)
                {
                    itter.next =itter.next.next;
                    cleanUp();
                    return;
                }
                itter = itter.next;
            }
            //System.out.println("Not Found");
            return;
            
        }
        public void removeLetter(String a)
        {
            Node itter = head;
                while( itter.next != null )
                {
                    if ( a.toUpperCase().charAt(0) == itter.next.get().toUpperCase().charAt(0) )
                    {
                        Node itter2 =itter.next;
                        while (itter2.next != null)
                        {
                            if ( a.toUpperCase().charAt(0) != itter2.next.get().toUpperCase().charAt(0) )
                            {
                                itter.next = itter2.next;
                                cleanUp();
                                return;
                            }
                            itter2 = itter2.next;
                        }
                        itter.next = null;
                        cleanUp();
                        return;
                    }
                    itter = itter.next;
                }
        }
        public boolean find (String a)
        {
            Node n = head;
            while (n != null)
            {
                if (n.get().compareTo(a) == 0)
                {
                    return true;
                }
                else if (n.next == null)
                {
                    return false;
                }
                n= n.next;
            }
            return false;

        }
        public void cleanUp()
        {
            Node itter = head;
            if (itter.next == null)
            {
                head = null;
                return;
            }
            else if (itter.next.get().length() == 1)
            {
                head = itter.next;
            }

            while (itter.next != null)
            {
                if (itter.next.get().length() == 1 )
                {
                    Node itter2 = itter.next;
                    if (itter2.next == null)
                    {
                        itter.next = null;
                        return;
                    }

                    else if (itter2.next.get().length()==1)
                    {
                        itter.next =itter2;
                        return;
                    }
                    
                }
                itter = itter.next;
            }
            return;
        }
    
    
    private static class Node
    {
        private String a;
        Node next = null;
        Node (String a)
        {
            this.a = a;
        }
        public String get()
        {
            return a;
        }
    }

}

