Programming Assignment 1:

Polynomial

In this assignment, you will implement a polynomial and operations on it using a linked list.

Worth 60 points (6% of course grade)

Posted Fri, Feb 1

Due Fri, Feb 15, 11:00 PM 

Read Section 3.1 in the textbook for background on polynomials and polynomial arithmetic.

A polynomial may be represented using a linked list as follows: for every term in the polynomial there is one entry in the linked list consisting of the term's coefficient and degree. The entries are ordered according to ASCENDING values of degree, i.e. lowest degree term first, then next lowest degree term and so on, all the way up to the highest degree term. IMPORTANT: Zero-coefficient terms are NOT stored.

For example, the following polynomial (the symbol '^' is used to mean 'raised to the power'):
      4x^5 - 2x^3 + 2x +3
      
can be represented as the linked list of terms:
                         
(3,0) -> (2,1) -> (-2,3) -> (4,5)

where each term is a (coefficient,degree) pair. Notes about representation:
Terms are stored in ASCENDING order of degrees from front to rear in a non-circular linked list. Zero-coefficient terms are NOT stored.
An EMPTY (zero) polynomial is represented by a linked list with NO NODES in it, i.e. referenced by NULL. Coefficients are floating point numbers
Degrees are POSITIVE integers, except if there is a constant term, in which case the degree is zero. There will not be more than one term in the same degree.
If you do not represent all your polynomials (the initial inputs as well as those you get out of doing arithmetic on polynomials) as above, you will lose credit even if your results are mathematically correct.

MORE INFO: Assignment 1 description.pdf
