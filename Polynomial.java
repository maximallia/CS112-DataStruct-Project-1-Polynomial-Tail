package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 *
 * @author runb-cs112
 *
 */
public class Polynomial {

	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3
	 * </pre>
	 *
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc)
			throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}

	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 *
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION


		Node poly = null ;
		Node ptr = null ;
		int maxDegree = 0 ;

		while ( poly1 != null && poly2 != null ) {
			float coeff = 0 ;
			int degree = 0 ;
			if ( poly1.term.degree == poly2.term.degree ) {
				coeff = poly1.term.coeff + poly2.term.coeff ;
				degree = poly1.term.degree ;
				poly1 = poly1.next ;
				poly2 = poly2.next ;
			} else if ( poly1.term.degree < poly2.term.degree ) {
				coeff = poly2.term.coeff ;
				degree = poly2.term.degree ;
				poly2 = poly2.next ;
			} else {
				coeff = poly1.term.coeff ;
				degree = poly1.term.degree ;
				poly1 = poly1.next ;
			}

			if ( maxDegree < degree ) maxDegree = degree ;

			Node nnode = new Node( coeff, degree, null ) ;
			if ( nnode.term.coeff == 0 ) {
				nnode = nnode.next ;
			}


			if ( poly == null ) {
				poly = nnode ;
				ptr = nnode ;
			} else {
				ptr.next = nnode ;
				ptr = ptr.next ;
			}
		}

		while ( poly1 != null ) {
			Node nnode = new Node (poly1.term.coeff, poly1.term.degree, null ) ;
			if ( poly == null ) {
				poly = ptr = nnode ;
			} else {
				ptr.next = nnode ;
				ptr = ptr.next ;
			}

			poly1 = poly1.next ;
		}

		while ( poly2 != null ) {
			Node nnode = new Node ( poly2.term.coeff, poly2.term.degree, null ) ;
			if ( poly == null ) {
				poly = ptr = nnode ;
			} else {
				ptr.next = nnode ;
				ptr = ptr.next ;
			}
			poly2 = poly2.next ;
		}

		Node npoly = null ;
		while (maxDegree >= 0 ) {
			Node temp = poly ;
			float total = 0 ;
			while ( temp != null ) {
				if ( temp.term.degree == maxDegree ) {
					total += temp.term.coeff ;
				}
				temp = temp.next ;
			}
			if (total != 0) {
				npoly = new Node (total, maxDegree, npoly ) ;
			}

			maxDegree-- ;
		}
		return npoly ;
	}

	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 *
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */

	public static Node multiply(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION

		if (poly1 == null || poly2 == null ) return null ;

		Node poly = null ;
		Node current = poly1 ;
		Node current2 = poly2 ;

		int maxDegree = 0 ;


		while ( current != null ) {
			while (current2 != null ) {
				float coeff = current.term.coeff * current2.term.coeff ;
				int degree = current.term.degree + current2.term.degree ;
				poly = new Node ( coeff, degree, poly ) ;

				if ( maxDegree < degree ) maxDegree = degree ;

				current2 = current2.next ;
			}
			current2 = poly2 ;
			current = current.next ;
		}

		Node npoly = null ;
		while (maxDegree >= 0 ) {
			Node temp = poly ;
			float total = 0 ;
			while ( temp != null ) {
				if ( temp.term.degree == maxDegree ) {
					total += temp.term.coeff ;
				}
				temp = temp.next ;
			}
			if (total != 0) {
				npoly = new Node (total, maxDegree, npoly ) ;
			}

			maxDegree-- ;
		}
		return npoly ;
	}


	/**
	 * Evaluates a polynomial at a given value.
	 *
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION

		float value = 0 ;
		while ( poly != null ) {
			value += poly.term.coeff * Math.pow(x, poly.term.degree ) ;
			poly = poly.next ;
		}
		return value;
	}

	/**
	 * Returns string representation of a polynomial
	 *
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		}

		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
			 current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}
}