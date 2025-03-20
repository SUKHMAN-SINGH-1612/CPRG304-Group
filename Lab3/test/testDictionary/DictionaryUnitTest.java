/**
 * 
 */
package testDictionary;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.DuplicateKeyException;
import implementations.Dictionary;

/**
 * @author kitty
 */
public class DictionaryUnitTest
{

	Dictionary<Integer,String> myList;
	static Integer one;
	static Integer two;
	static String a;
	static String b;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		one = Integer.valueOf( 1 );
		two = Integer.valueOf( 2 );
		a = "A";
		b = "B";
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		one = two = null;
		a = b = null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		myList = new Dictionary<Integer,String>( 5 );
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		myList = null;
	}

	/**
	 * Test method for
	 * {@link implementations.Dictionary#create(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public void testCreate_DefaultSize() //will always pass because of default constructors in Java
	{
		boolean expected = true;
		boolean actual = new Dictionary<Integer,String>() != null;
		assertEquals( "Dictionary was not created ", expected, actual );
	}


	/**
	 * Test method for
	 * {@link implementations.Dictionary#create(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public void testCreate_SpecifySize() //will always pass because of default constructors in Java
	{
		boolean expected = true;
		boolean actual = new Dictionary<Integer,String>(50) != null;
		assertEquals( "Dictionary was not created ", expected, actual );
	}
	
	/**
	 * Test method for
	 * {@link implementations.Dictionary#insert(java.lang.Object, java.lang.Object)}.
	 * @throws DuplicateKeyException 
	 */
	@Test
	public void testInsert() throws DuplicateKeyException
	{
		boolean expected = true;
		boolean actual = myList.insert( one, a );
		assertEquals("Insert did not return boolean correctly.", expected, actual);
	}
	
	/**
	 * Test method for
	 * {@link implementations.Dictionary#insert(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public void testInsert_Exception()
	{
		try
		{
			myList.insert( one, a );
			myList.insert( one, a );
			fail( "Insert did not throw Exception." );
		}
		catch( DuplicateKeyException e )
		{
			// do nothing and the test will pass
			// or return; 
			// or assertTrue(true); 
		}
	}

	/**
	 * Test method for {@link implementations.Dictionary#remove(java.lang.Object)}.
	 * @throws DuplicateKeyException 
	 */
	@Test
	public void testRemove() throws DuplicateKeyException
	{
		myList.insert( one, a );
		myList.insert( two, b );
		
		String expected = "A";
		String actual = myList.remove( one );
		assertEquals("Remove did not return value correctly.", expected, actual);
	}
	
	/**
	 * Test method for {@link implementations.Dictionary#remove(java.lang.Object)}.
	 * @throws DuplicateKeyException 
	 */
	@Test
	public void testRemove_NoMatch() throws DuplicateKeyException
	{
		myList.insert( one, a );

		String expected = null;
		String actual = myList.remove( two );
		assertEquals("Remove did not return value correctly.", expected, actual);
	}

	/**
	 * Test method for
	 * {@link implementations.Dictionary#update(java.lang.Object, java.lang.Object)}.
	 * @throws DuplicateKeyException 
	 */
	@Test
	public void testUpdate() throws DuplicateKeyException
	{
		myList.insert( one, a );
		
		Boolean expected = true;
		Boolean actual = myList.update( one, b );
		assertEquals("Update did not execute correctly.", expected, actual);
	}
	
	/**
	 * Test method for
	 * {@link implementations.Dictionary#update(java.lang.Object, java.lang.Object)}.
	 * @throws DuplicateKeyException 
	 */
	@Test
	public void testUpdate_NoMatch() throws DuplicateKeyException
	{
		myList.insert( one, a );
		
		Boolean expected = false;
		Boolean actual = myList.update( two, b );
		assertEquals("Update did not execute correctly.", expected, actual);
	}

	/**
	 * Test method for {@link implementations.Dictionary#lookup(java.lang.Object)}.
	 * @throws DuplicateKeyException 
	 */
	@Test
	public void testLookup() throws DuplicateKeyException
	{
		myList.insert( one, a );
		
		String expected = "A";
		String actual = myList.lookup( one );
		assertEquals("Lookup did not return the correct value.", expected, actual);
	}

	/**
	 * Test method for {@link implementations.Dictionary#lookup(java.lang.Object)}.
	 * @throws DuplicateKeyException 
	 */
	@Test
	public void testLookup_NoMatch() throws DuplicateKeyException
	{
		myList.insert( one, a );
		
		String expected = null;
		String actual = myList.lookup( two );
		assertEquals("Lookup did not return the correct value.", expected, actual);
	}
}
