package com.tecnositaf.centrobackend.enumeration;

public enum Errors {

	INVALID_REQUEST(1, "The request is invalid"),
	INVALID_FIELD(2, "Empty/Invalid Field"),
	INVALID_USERNAME_OR_PASSWORD(3, "Invalid Username or Password"),
	RESULT_NOT_FOUND(4, "No Result Found"),
	INVALID_TOKEN(5, "InvalidToken"),
	EXPIRED_TOKEN(6, "ExpiredToken"),
	EXCEPTION_TOKEN(7, "ExceptionToken"),
	INVALID_FORMAT_DATE(8, "Invalid Format Date"),
	INVALID_FORMAT_DATE_TIME(9, "Invalid Format DateTime"),
	INVALID_ID_FIREBASE(14, "Invalid id Firebase"),
	UNAUTHORIZED_USER(20, "User not Authorized"),

	NOT_ALLOWED(21, "OperationNotAllowed"),

	DATABASE_ERROR(31, "A database error has occured."),

	MISSING_PARAMETER(41, "Required query parameter is missing"), MISSING_HEADER(42, "Required header is missing"),

	DUPLICATE_USER(51, "This user already exists."),

	PARSING_JACKSON_EXCEPTION(61, "Jackson was not able to parse."),

	UNEXPECTED_ERROR(500, "UnexpectedError");

	public final int code;
	public final String description;
	public static final String SUCCESS = "Success";
	public static final String FAILURE = "Failure";

	private Errors(int code, String description) {
		this.code = code;
		this.description = description;
	}

	@Override
	public String toString() {
		return code + " : " + description;
	}
}