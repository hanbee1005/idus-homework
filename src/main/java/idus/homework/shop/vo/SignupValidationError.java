package idus.homework.shop.vo;

public enum SignupValidationError {
    NAME_NON_CONFORM("이름은 한글, 영문 대소문자만 허용합니다."),
    NAME_LENGTH_NON_CONFORM("이름은 최대 20자만 허용합니다."),
    NICK_NAME_NON_CONFORM("별명은 영문 소문자만 허용합니다."),
    NICKNAME_LENGTH_NON_CONFORM("별명은 최대 30자만 허용합니다."),
    PASSWORD_NON_CONFORM("비밀번호는 영문 대문자, 영문 소문자, 특수 문자, 숫자를 각 1개 이상씩 포함하는 10자 이상의 문자여야 합니다."),
    PHONENUM_NON_CONFORM("전화번호는 숫자만 허용합니다."),
    PHONENUM_LENGTH_NON_CONFORM("전화번호는 최대 20자만 허용합니다."),
    EMAIL_NON_CONFORM("이메일 형식에 맞지 않습니다."),
    EMAIL_LENGTH_NON_CONFORM("이메일은 최대 100자만 허용합니다.");


    SignupValidationError(String value) { this.value = value; }

    private final String value;
    public String value(){ return value; }
}
