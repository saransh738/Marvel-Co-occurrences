import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;
public class A4_2019MT60763
{
	   //function to calculate the average 
	    public static void average(GRAPH G)
	    {
		   double e1 = G.getEdgesCount();
           	   double v1 = G.getVertexCount();
   		   double tot = e1/v1;
		   System.out.printf("%.2f",tot);
		   System.out.print("\n");
		   
	    }
           //function to find rank 		
	   public static void rank(GRAPH g)
           {
           	   g.RANKO();
           }
	   //function to find independent storylines using DFS
	   public static void independent_storylines_dfs(GRAPH g)
	   {
		   g.dfs();
           }
		//main 
		public static void main(String[] args) throws Exception 
		{
			// to take input
			String NODEFILE = args[0];
			String EDGEFILE = args[1];
			String SARANSH = args[2];
			//to construct a graph
		    	GRAPH g = new GRAPH();
			//regrex used to split the string
            		String splitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
			//to read file using BufferedReader using FileReader
            		BufferedReader br1 = new BufferedReader(new FileReader(NODEFILE));
			BufferedReader br2 = new BufferedReader(new FileReader(EDGEFILE));
			String line1;
			int i1 = 0;
            		while((line1 = br1.readLine()) !=null )
		    	{
				if(i1 == 0)
				{
				    	i1 ++;
				        continue;
				}
                	String[] b1 = line1.split(splitBy);
			//To remove double quotes from the string  
			if (b1[1].charAt(0) == '"' && b1[1].charAt(b1[1].length()-1) == '"')
			    {
                   		b1[1] = b1[1].substring(1,b1[1].length()-1);
                	    }
                	g.ADDNODES(b1[1]);
            		} 
			br1.close();
			String line2;
			int i2 = 0;
			while((line2 = br2.readLine()) !=null )
		    	{
			    if(i2 == 0)
			    {
				   i2++;
			           continue;
			    }
                	String[] u = line2.split(splitBy);
			//To remove double quotes from the string 
			if (u[1].charAt(0) == '"' && u[1].charAt(u[1].length()-1) == '"')
			{
                     		u[1] = u[1].substring(1,u[1].length()-1);
                	}
			if (u[0].charAt(0) == '"' && u[0].charAt(u[0].length()-1) == '"')
			{
                     		u[0] = u[0].substring(1,u[0].length()-1);
                	}
			g.ADDEDGE(u[0], u[1], Integer.parseInt(u[2]));
            } 
	    br2.close();
		//switch case used to use functions
		switch(SARANSH)
		{
			case "average":
			    average(g);
				break;
			case "rank":
               		    rank(g);
				break;
			case "independent_storylines_dfs":
                	    independent_storylines_dfs(g);
                		break;	
			default:
				break;				
		}
	}
}
    // A variable which stores the node and weight associated with it
    class NODE
	    {
		    String NODES ;
		    int distance;
		    NODE(String v, int q)
		    {	
                       NODES = v;
                       distance = q;
		    }
             }
	class GRAPH
	{
		// Hashmap is used to store weights associated with each edge of graph 
		HashMap<String, Integer>  WEIGHTS = new HashMap<>();
                // Hashmap is used to store edges of graph 
        	HashMap<String, LinkedList<NODE>> MAP = new HashMap<>();
		int i = -1;
        // This function adds a new vertex to the graph 
        public void ADDNODES(String c) 
	    {
            	MAP.put(c, new LinkedList<NODE>());
		WEIGHTS.put(c, ++i);
            }
	
        // This function adds the edge 
        // between source to destination 
        public void ADDEDGE(String s, String t, int u) 
	    {
            NODE A1 = new NODE(t,u);
            NODE A2 = new NODE(s,u);
            MAP.get(s).add(A1);
            MAP.get(t).add(A2);
            }
            // This function gives the count of vertices 
            public  int getVertexCount() 
	    { 
		return MAP.keySet().size() ; 
	    }
	    // This function gives the count of edges 
		public  int getEdgesCount() 
		{ 
			int count = 0; 
			for (String s : MAP.keySet()) 
			{	 
				count = count + MAP.get(s).size(); 
			}
			return count ; 
		}
                public void RANKO()
	        {
			    Vector<NODE> k = new Vector<>();
			    for( String d : MAP.keySet())
			    {
                    			int s = 0;
					LinkedList<NODE> AV = MAP.get(d);
					for (NODE v:AV)
					{
						s = s + v.distance;
					}
					NODE o = new NODE(d,s);
					k.add(o);
               		    }
				k = MS(k,0,MAP.keySet().size()-1);
				int j = 0;
                		for(j = 0; j<MAP.keySet().size(); j++)
				{
                    			System.out.print(k.get(j).NODES);
                    			if (j == MAP.keySet().size()-1)
					{
						continue;
					}
                    			System.out.print(',');
                		}
				System.out.println(" ");
			}
	
        public static Vector<NODE> MS(Vector<NODE> a,int S,int E)
		{
	        	if (S == E)
			{
	             		return a;
	        	}
			int M = (S+E)/2;
			MS(a,S,M);
			MS(a,M+1,E);
			return MERGO(a,S,M,E);
	    	}
	public static Vector<NODE> MERGO(Vector<NODE> a,int L,int M,int H)
		{
			int f1 = M-L+1;
			int f2 = H-M;
			NODE[] t1 = new NODE[f1];
			NODE[] t2 = new NODE[f2];
			int j;
			for (j = 0; j < f1; j++)
			{
	        	     t1[j] = a.get(L+j);
			}
			int e;
			for (e = 0; e < f2; e++)
			{
				t2[e] = a.get(M+e+1);
			}
			int b1 = 0;
			int b2 = 0;
			int b3 = L;
			while (b1 < f1 && b2 < f2)
			{
				if (t1[b1].distance > t2[b2].distance)
				{
					a.set(b3,t1[b1]);
					b1++;
				}
				else if (t1[b1].distance < t2[b2].distance)
				{
					a.set(b3,t2[b2]);
					b2++;
				}
				else
				{
					if (t1[b1].NODES.compareTo(t2[b2].NODES) > 0)
					{
						a.set(b3,t1[b1]);
						b1++;
					}
					else
					{
						a.set(b3,t2[b2]);
						b2++;
					}
				}
				b3++;
			}
			while (b1 < f1)
			{
				a.set(b3,t1[b1]);
				b1++;
				b3++;
			}
			while (b2 < f2)
			{
	 			a.set(b3,t2[b2]);
				b2++;
				b3++;
			}
			return a;
	}
	
	public void dfs()
	{
            int l = MAP.size();
            Vector<NODE> u = new Vector<>();
	    boolean [] s = new boolean[l];
            for (HashMap.Entry<String, LinkedList<NODE>> y : MAP.entrySet()) 
	    {
                	String p = y.getKey();
                	if(!s[WEIGHTS.get(p)])
			{
                    		Vector<NODE> h = new Vector<>();
                    		hipo(p,s,h);
				Vector<NODE>T = MS(h,0,h.size()-1);
				StringBuilder q = new StringBuilder();
				int j;
                    		for ( j = 0; j <T.size()-1 ; j++)
				{
                         		q.append(T.get(j).NODES).append(",");
                    		}
                    	 q.append(T.get(T.size()-1).NODES).append("");
			 String R = q.toString();
                    NODE O = new NODE(R,T.size());
                    u.add(O);
                         }
	     }
         	 Vector<NODE> T = MS(u,0,u.size()-1);
		 int k;
         	for ( k = 0; k < T.size(); k++)
		{
             		System.out.println(T.get(k).NODES);
	     	}
	}

        public void hipo(String w, boolean[] s,Vector<NODE> e )
	{
            s[WEIGHTS.get(w)] = true;
            NODE c = new NODE(w,0);
            e.add(c);
            LinkedList<NODE> L = MAP.get(w);
	    int j;
            for ( j = 0; j <L.size() ; j++)
	    {
                String D = L.get(j).NODES;
                if(!s[WEIGHTS.get(D)])
		{
                    hipo(D,s,e);
		}
            }
        }
}

	 
  
