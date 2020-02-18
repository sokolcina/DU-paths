package du_paths;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


/*
 * Created by Stefan Sokolovic 2018 
 */

public class Graph {

	private HashMap<Node,ArrayList<Node>> graph;
	public static HashMap<Integer,Node> nodes=new HashMap<>();
	private int n,m;
	public Graph()
	{
		graph=new HashMap<>();
	}
	
	public void readAndBuildGraph(Scanner sc)
	{
		
		n=sc.nextInt();
		m=sc.nextInt();
		for(int i=0;i<n;i++)
		{
			nodes.put(i, new Node(i,false,false));
			graph.put(nodes.get(i), new ArrayList<>());
		}
		for(int i=0;i<m;i++)
			graph.get(nodes.get(sc.nextInt())).add(nodes.get(sc.nextInt()));
	}
	public void reset()
	{
		for(Node n : nodes.values())
		{
			n.setDef(false);
			n.setUse(false);
		}
	}
	public void search(Node s, PrintWriter p)
	{
		ArrayList<Node> list=graph.get(s);
		for(Iterator<Node> it=list.iterator();it.hasNext();)
		{
			ArrayList<Integer> tmp=new ArrayList<>();
			tmp.add(s.getNode());
			searchHelp(it.next(),tmp,p);
		}
		
	}
	private void searchHelp(Node s, ArrayList<Integer> res,PrintWriter p)
	{
		res.add(s.getNode());
		if(res.size()>1)
		{
			int i=res.size()-2;
			while(i>=0)
			{
				if(res.get(i)==res.get(res.size()-1))
				{
					if(i==0 && s.isUse())
						{
						p.println(res);
						return;
						}
					else
						return;
				}
				i--;
			}
		}
		if(s.isDef() && !s.isUse())
			return;
		if(s.isUse())
		{
			if(s.isUse() && !s.isDef())
			{
				p.println(res);			
				if(graph.get(s)!=null)
					for(Iterator<Node> it = graph.get(s).iterator();it.hasNext();)
					{
						Node tmp=it.next();
						searchHelp(tmp,res,p);
						res.remove(res.size()-1);
					}
			}
			else
			{
					p.println(res);
					return;
			}
		}
		else
			if(graph.get(s)!=null)
				for(Iterator<Node> it = graph.get(s).iterator();it.hasNext();)
						{
							Node tmp=it.next();
							searchHelp(tmp,res,p);
							res.remove(res.size()-1);
						}
		
	}
	
	public void print()
	{
		for(Node cvor : graph.keySet())
		{
			System.out.println(cvor);
		for(Iterator<Node> it= graph.get(cvor).iterator();it.hasNext();)
	
				System.out.print(it.next()+" ");		
		System.out.println();
		System.out.println();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g=new Graph();
		
		FileReader fr;
		try {
			fr = new FileReader("Input.dat");
			Scanner sc = new Scanner(fr);
			PrintWriter writer=new PrintWriter("Output.dat");
			g.readAndBuildGraph(sc);
			sc.nextLine();
			
			
			while(sc.hasNextLine())
			{
				writer.println(sc.nextLine());
				String [] str=sc.nextLine().split(" ");
				int [] niz=new int [str.length];
				
				for(int i=0;i<str.length;i++)
				{
					niz[i]=Integer.parseInt(str[i]);
					nodes.get(niz[i]).setDef(true);
				}
				
				str=sc.nextLine().split(" ");
				
				for(int i=0;i<str.length;i++)
					nodes.get(Integer.parseInt(str[i])).setUse(true);
				
				for(int i=0;i<niz.length;i++)
					g.search(nodes.get(niz[i]), writer);
				
				writer.println();
				g.reset();
			}
			//g.print();
			sc.close();
			writer.close();
			try {
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
