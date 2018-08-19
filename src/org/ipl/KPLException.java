package org.ipl;

/**
 * Enum constant for exception messages used inside the org.ipl package
 */
enum CustomExceptionMessages {
    Has_No_Player, Target_Score_Negative, No_Match_With_Zero_Overs, Invalid_Ball_Outcome, Error_Finding_Random
}

/**
 * Implements the custom exception class.
 */
class KPLException extends Exception {
    static final long serialVersionUID = 2L;

    KPLException(String message) {
        super(message);
    }

}
