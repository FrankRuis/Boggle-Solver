package solver;

/**
 * Enum containing the value for each letter
 * 
 * @author Frank
 */
public enum CharVal {
	
	A(1),B(4),C(5),D(2),E(1),F(4),G(3),H(4),I(2),J(4),K(3),L(3),M(3),N(1),O(1),P(4),Q(8),R(2),S(2),T(2),U(2),V(4),W(5),X(8),Y(5),Z(5);
	
	private int val;
	
	private CharVal(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}
