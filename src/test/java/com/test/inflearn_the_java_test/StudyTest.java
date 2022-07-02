package com.test.inflearn_the_java_test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import java.time.Duration;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {
    // 실패와 상관없이 문자열을 연산을 하기 때문에, 복잡한 연산이 필요한 경우 람다식을 사용하자


    // 가급적 문자열의 오타를 줄이기 위해서 어노테이션을 만들어 놓기
    @FastTest
    @DisplayName("스터디 만들기 fast")
    void create_new_study() {
        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @SlowTest
    @DisplayName("스터디 만들기 slow")
    void create_new_study_again() {
        System.out.println("create1");
    }

    @DisplayName("스터디 만들기")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeatTest(RepetitionInfo repetitionInfo) {
        System.out.println("test" + repetitionInfo.getCurrentRepetition() + "/" +
                repetitionInfo.getTotalRepetitions());
    }


    @DisplayName("스터디 만들기")
    // 다른값들로 테스트 해보고 싶으면
    @ParameterizedTest(name = "{index} {displayName}  maessage={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
        // 두개의 인자값을 받아서 study 인자값을 받은 인스턴스를 만들기
    void parameterizedTest(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }

    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return new Study(accessor.getInteger(0), accessor.getString(1));
        }
    }


//    @DisplayName("스터디 만들기")
//    // 다른값들로 테스트 해보고 싶으면
//    @ParameterizedTest(name = "{index} {displayName}  maessage={0}")
//    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
//    // 암묵적으로 바꾸고자 하는 타입을 변환을 해줌
//    void parameterizedTest(ArgumentsAccessor argumentsAccessor) {
//        Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
//        System.out.println(study);
//    }
//
//    // 아규먼트컨버터 : 하나의 아규먼트를 다른 타입으로 변환할 떄 적용
//    static class StudyConverter extends SimpleArgumentConverter {
//
//        @Override
//        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
//            assertEquals(Study.class, targetType, "Can only convert to Study");
//            return new Study(Integer.parseInt(source.toString()));
//        }
//    }

//    @DisplayName("스터디 만들기")
//    // 다른값들로 테스트 해보고 싶으면
//    @ParameterizedTest(name = "{index} {displayName}  maessage={0}")
//    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
//        // 암묵적으로 바꾸고자 하는 타입을 변환을 해줌
//    void parameterizedTest(Integer limit, String name) {
//        Study study = new Study(limit, name);
//        System.out.println(study);
//    }
//
//    // 아규먼트컨버터 : 하나의 아규먼트를 다른 타입으로 변환할 떄 적용
//    static class StudyConverter extends SimpleArgumentConverter {
//
//        @Override
//        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
//            assertEquals(Study.class, targetType, "Can only convert to Study");
//            return new Study(Integer.parseInt(source.toString()));
//        }
//    }

//    @DisplayName("스터디 만들기")
//    // 다른값들로 테스트 해보고 싶으면
//    @ParameterizedTest(name = "{index} {displayName}  maessage={0}")
//    // 파라미터들을 정의할 수 있음
//    @ValueSource( ints =  {10, 20,40})
//    // 암묵적으로 바꾸고자 하는 타입을 변환을 해줌
//    void parameterizedTest(@ConvertWith(StudyConverter.class) Study study) {
//        System.out.println(study.getLimit());
//    }
//
//
//    static class StudyConverter extends SimpleArgumentConverter {
//
//        @Override
//        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
//            assertEquals(Study.class, targetType, "Can only convert to Study");
//            return new Study(Integer.parseInt(source.toString()));
//        }
//    }

//    @DisplayName("스터디 만들기")
//    // 다른값들로 테스트 해보고 싶으면
//    @ParameterizedTest(name = "{index} {displayName}  maessage={0}")
//    // 파라미터들을 정의할 수 있음
//    @ValueSource( strings =  {"날씨가", "많이", "더워지고", "있네요."})
//    @NullAndEmptySource
//    void parameterizedTest(String message) {
//        System.out.println(message);
//    }

//    @Test
//    @DisplayName("스터디 만들기")
//    // 운영체제
//    @EnabledOnOs({OS.WINDOWS, OS.LINUX})
//    // 자바버전
//    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_10, JRE.JAVA_11, JRE.JAVA_17})
//    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")
//    void create_new_study() {
//        // 테스트 환경이 로컬인가?
//        String test_env = System.getenv("TEST_ENV");
//
//        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
//            System.out.println("local");
//            Study actual = new Study(100);
//            assertThat(actual.getLimit()).isGreaterThan(0);
//        });
//
//        assumingThat("sounghun".equalsIgnoreCase(test_env), () -> {
//            System.out.println("sounghun");
//            Study actual = new Study(10);
//            assertThat(actual.getLimit()).isGreaterThan(0);
//        });
//    }


//    @Test
//    @DisplayName("스터디 만들기")
//    void create_new_study() {
//        Study actual = new Study(10);
//        assertThat(actual.getLimit()).isGreaterThan(0);
//
//    }

//    @Test
//    @DisplayName("스터디 만들기")
//    void create_new_study() {
//        // 코드블록은 별도로 실행하기 때문에
//       assertTimeoutPreemptively(Duration.ofMillis(10), () -> {
//           new Study(10);
//           Thread.sleep(300);
//       });
////       // TODO ThreadLocal
////        //  에서 사용하는 코드가 있을 경우, 예상치 못한 문제가 발생 할 수 있음
////        // 스프링 트렌젝션 쓰레드 로컬을 기본으로 사용하는데
////        // 다른 스레드에서 공유가 안됨
////        // 트렌젝션 설정이 제대로 적용이 안될 수 있음 (롤백을 기본)
////        // 바로 DB로 들어갈 수 있기 때문에, 주의해서 사용하세요.
////        // 안전하게 사용할려면 assertTimeout을 사용하세요.
////
//    }

//    @Test
//    @DisplayName("스터디 만들기")
//    void create_new_study() {
//        assertTimeout(Duration.ofMillis(10), () -> {
//            new Study(10);
//            Thread.sleep(300);
//        });
//    }

//    @Test
//    @DisplayName("스터디 만들기")
//    void create_new_study() {
//        assertTimeout(Duration.ofSeconds(10), () -> new Study(10));
//    }

//    @Test
//    @DisplayName("스터디 만들기")
//    void create_new_study() {
//        // 익셉션을 파라미터로 받아서
//        IllegalArgumentException exception =
//                assertThrows(IllegalArgumentException.class, () -> new Study(-10));
//        String message = exception.getMessage();
//        assertEquals("limit은 0보다 커야 한다.", exception.getMessage());
//    }

//    Test
//    @DisplayName("스터디 만들기")
//    void create_new_study() {
//        Study study = new Study(-10);
//
//        assertAll(
//
//                () -> assertNotNull(study),
//                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(),
//                        () -> "스터디를 처음 만들면 상태값이 DRAFT여야 한다."),
//                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다.")
//        );
//    }

//    @Test
//    @DisplayName("스터디 만들기")
//    void create_new_study1() {
//        Study study = new Study();
//        assertNotNull(study);
//        // 람다식으로 하면 테스트가 실패 했을 때만 연산을 홤
//        assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DRAFT여야 한다.");
//    }
//
//
//    @Test
//    @DisplayName("스터디 만들기")
//    void create_new_study2() {
//        Study study = new Study();
//        assertNotNull(study);
//        assertEquals(StudyStatus.DRAFT, study.getStatus(), new Supplier<String>() {
//            @Override
//            public String get() {
//                return "스터디를 처음 만들면 상태값이 DRAFT여야 한다.";
//            }
//        });
//    }

//    @Test
//    @DisplayName("스터디 만들기2")
////    @DisabledOnOs(OS.WINDOWS)
////    @DisabledOnJre(JRE.OTHER)
//    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "sounghun")
//    void create_new_study_again() {
//        System.out.println("create1");
//    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }


    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }

}