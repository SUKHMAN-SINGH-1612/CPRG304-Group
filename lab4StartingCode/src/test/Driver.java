package test;

import java.util.Iterator;

import exercise1.MyAList;
import exercise2.MyDList;
import exercise2.MyNode;

public class Driver
{

	public static void main( String[] args )
	{

		// testing AList implementation
		MyAList<String> aListA = new MyAList<>();
		MyAList<String> aListB = new MyAList<>();

		// testing new object
		System.out.println( "Size Before Add: " + aListA.size() );
		System.out.println( "IsEmpty Before Add? " + aListA.isEmpty() );

		// testing add() (Thanks ChatGPT for the names!)
		aListA.add( "Alice" );
		aListA.add( "Bob" );
		aListA.add( "Charlie" );
		aListA.add( "David" );
		aListA.add( "Eva" );
		aListA.add( "Frank" );
		aListA.add( "Gina" );
		aListA.add( "Harry" );
		aListA.add( "Irene" );
		aListA.add( "Jack" );
		for( String name : aListA )
		{
			System.out.println( "ListA: " + name );
		}

		aListB.add( "John" );
		aListB.add( "Emma" );
		aListB.add( "Liam" );
		aListB.add( "Olivia" );
		aListB.add( "Noah" );
		aListB.add( "Ava" );
		aListB.add( "William" );
		aListB.add( "Sophia" );
		aListB.add( "James" );
		aListB.add( "Mia" );
		for( String name : aListB )
		{
			System.out.println( "ListB: " + name );
		}

		// testing addAll()
		aListA.addAll( aListB );
		for( String name : aListA )
		{
			System.out.println( "After addAll(): " + name );
		}

		// testing get()
		System.out.println( "Get 0: " + aListA.get( 0 ) );

		// testing remove()
		aListA.remove( 0 );
		System.out.println( "Get After Remove 0: " + aListA.get( 0 ) );

		// testing set()
		aListA.set( 0, "Bub" );
		System.out.println( "Get After Set 0: " + aListA.get( 0 ) );

		// testing size()
		System.out.println( "Size After Operations: " + aListA.size() );

		// testing isEmpty()
		System.out.println( "IsEmpty After Operations? " + aListA.isEmpty() );

		// testing toArray()
		Object[] newArray = aListA.toArray();
		for( int i = 0; i < newArray.length; i++ )
		{
			System.out.println( "ToArray: " + newArray[i] );
		}

		// testing iterator()
		Iterator<String> it = aListA.iterator();
		while( it.hasNext() )
		{
			System.out.println( "Iterator: " + it.next() );
		}

		// testing clear()
		aListA.clear();
		System.out.println( "Size After Clear: " + aListA.size() );
		System.out.println( "IsEmpty After Clear? " + aListA.isEmpty() );

		System.out.println( "\n\n" );

		// testing DList implementation
		MyDList<Integer> dList = new MyDList<>();

		// testing new object
		System.out.println( "Size: " + dList.size() );
		System.out.println( "IsEmpty? " + dList.isEmpty() );

		// testing addFirst() (Thanks ChatGPT for the numbers!)
		dList.addFirst( 10 );
		dList.addFirst( 25 );
		dList.addFirst( 8 );
		dList.addFirst( 17 );
		dList.addFirst( 42 );
		dList.addFirst( 56 );
		dList.addFirst( 33 );
		dList.addFirst( 71 );
		dList.addFirst( 94 );
		dList.addFirst( 12 );

		MyNode<Integer> curr = dList.getHead();
		for( int i = 0; i < dList.size(); i++ )
		{
			System.out.println( "Add First: " + curr.getElement() );
			curr = curr.getNext();
		}

		// testing addLast() (Thanks ChatGPT for the numbers)
		dList.addLast( 5 );
		dList.addLast( 18 );
		dList.addLast( 30 );
		dList.addLast( 47 );
		dList.addLast( 22 );
		dList.addLast( 14 );
		dList.addLast( 36 );
		dList.addLast( 59 );
		dList.addLast( 41 );
		dList.addLast( 13 );

		curr = dList.getHead();
		for( int i = 0; i < dList.size(); i++ )
		{
			System.out.println( "Added Last: " + curr.getElement() );
			curr = curr.getNext();
		}

		// testing removeFirst()
		Integer rm = dList.removeFirst();
		System.out.println( "Removed First: " + rm );

		// testing removeLast()
		rm = dList.removeLast();
		System.out.println( "Removed Last: " + rm );

		// testing size()
		System.out.println( "Size: " + dList.size() );

		// testing isEmpty()
		System.out.println( "IsEmpty? " + dList.isEmpty() );

	}

}
