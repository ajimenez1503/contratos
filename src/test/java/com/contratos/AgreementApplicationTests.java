package c

@GetMapping("/status")
public ResponseEntity<String> status(){
        return ResponseEntity.ok().build();
        }om.contratos;

        import org.junit.jupiter.api.Test;
        import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AgreementApplicationTests {

    @Test
    void contextLoads() {
    }

}
