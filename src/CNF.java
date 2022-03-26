/**
 * Author: Collin Campbell
 * Note: This was abandoned for reasons such as lines 44-50, this would be really cool to implement with Z3 SAT solver in c++ due to the face Z3 can take non CFG as an input.
 * Im assuming Z3 library converts the expr to CNF itself.
 */

/**
 * c  simple.cnf
 *       c
 *       p cnf 3 2 (p - problem, type, num of vars, num of clauses)
 *       1 -3 0 (var 1(true), var 3(false), end)
 *       2 3 -1 0   (var 2(true), var 3(true), var 1(False))
 *
 * simple.cnf stands for this boolean expr -> (1 OR NOT(3)) AND (2 OR 3 OR NOT(1))
 * OR - V
 * AND - ^
 */

public class CNF {
    int numVars;
    int numClauses;

    /**
     * For a 5X5 board there will be at max 25 vars, this only includes empty cells
     * @param numVars NUMBER OF BOOLS
     */
    CNF(int numVars){
        this.numVars = numVars;
    }

    /**
     * 3 Neighbors a, b, and c
     * 1 cell CNF form against wall-  (NOT a OR NOT b) AND (NOT a OR NOT c) AND (a OR b OR c) AND (NOT b OR NOT c)
     * 2 cell CNF form against wall - (NOT a OR NOT b or NOT c) AND (a OR b) AND (a OR c) AND (b OR c)
     * 3 cell CNF form against wall - (a) AND (b) AND (c)
     * ------------------------------
     * 2 Neighbors a, b
     * 1 cell CNF form corner - (NOT a OR NOT b) AND (a OR b)
     * 2 cell CNF form corner - (a) AND (b)
     * ------------------------------
     * 4 Neighbors a, b, c, d
     * 1 cell CNF - (¬a ∨ ¬b) ∧ (¬a ∨ ¬c) ∧ (¬a ∨ ¬d) ∧ (a ∨ b ∨ c ∨ d) ∧ (¬b ∨ ¬c) ∧ (¬b ∨ ¬d) ∧ (¬c ∨ ¬d)
     *
     * Im good -
     * 2 cell CNF - (¬B ∨ ¬C ∨ ¬A) ∧ (¬B ∨ ¬D ∨ ¬A) ∧ (C ∨ ¬D ∨ ¬B ∨ ¬A) ∧ (¬D ∨ B ∨ ¬C ∨ ¬A) ∧ (¬D ∨ ¬C ∨ ¬A) ∧ (¬B ∨ ¬C ∨ D ∨ ¬A)
     * ∧ (¬D ∨ ¬C ∨ B ∨ ¬A) ∧ (¬B ∨ ¬D ∨ C ∨ ¬A) ∧ (A ∨ ¬D ∨ ¬C ∨ ¬B) ∧ (¬B ∨ A ∨ ¬D ∨ ¬C) ∧ (A ∨ ¬C ∨ ¬D ∨ ¬B) ∧ (¬B ∨ ¬C ∨ A ∨ ¬D)
     * ∧ (¬D ∨ ¬C ∨ A ∨ ¬B) ∧ (¬B ∨ ¬D ∨ A ∨ ¬C) ∧ (¬D ∨ A ∨ ¬B ∨ ¬C) ∧ (A ∨ ¬C ∨ ¬B ∨ ¬D) ∧ (¬B ∨ ¬C ∨ ¬D) ∧ (¬D ∨ ¬C ∨ ¬B) ∧ (¬B ∨ ¬D ∨ ¬C)
     * ∧ (¬B ∨ A ∨ ¬C ∨ ¬D) ∧ (¬B ∨ A ∨ C ∨ D) ∧ (C ∨ A ∨ ¬B ∨ D) ∧ (¬B ∨ A ∨ D ∨ C) ∧ (A ∨ B ∨ C) ∧ (C ∨ A ∨ B) ∧ (¬D ∨ A ∨ B ∨ C) ∧ (C ∨ B ∨ A)
     * ∧ (¬D ∨ B ∨ A ∨ C) ∧ (C ∨ ¬D ∨ A ∨ B) ∧ (C ∨ B ∨ D) ∧ (¬D ∨ A ∨ C ∨ B) ∧ (C ∨ A ∨ ¬D ∨ B) ∧ (A ∨ B ∨ ¬D ∨ C) ∧ (A ∨ C ∨ D) ∧ (A ∨ D ∨ C)
     * ∧ (A ∨ B ∨ D) ∧ (A ∨ ¬C ∨ B ∨ D) ∧ (A ∨ B ∨ ¬C ∨ D) ∧ (A ∨ D ∨ B) ∧ (A ∨ ¬C ∨ D ∨ B)
     *
     * 3 cell CNF - (¬a ∨ ¬b ∨ ¬c ∨ ¬d) ∧ (a ∨ b) ∧ (a ∨ c) ∧ (a ∨ d) ∧ (b ∨ c) ∧ (b ∨ d) ∧ (c ∨ d)
     * 4 cell CNF - (a) AND (b) AND (c) AND (d)
     */
    //Every n-cell has exactly n adjacent lights

    /**
     * Visible cell from each light
     */
    //Every cell is lit up at least once

    /**
     * Sets
     */
    //Every row/column (seperated by wall) has at most one light(Bulb)
}
