package com.test.inflearn_the_java_test;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {
    // 실패와 상관없이 문자열을 연산을 하기 때문에, 복잡한 연산이 필요한 경우 람다식을 사용하자

    @Test
    @DisplayName("스터디 만들기")
    void create_new_study() {
       assertTimeout(Duration.ofMillis(10), () -> {
           new Study(10);
           Thread.sleep(300);
       });
    }

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

    @Test
    @DisplayName("스터디 만들기2")
    void create_new_study_again() {
        System.out.println("create1");
    }

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