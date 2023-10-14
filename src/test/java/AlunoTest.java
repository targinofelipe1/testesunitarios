import org.example.Aluno;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AlunoTest {

    @Test
    void testSetMatriculaValida() {
        Aluno aluno = new Aluno();
        aluno.setMatricula("12345678901");
        assertEquals("12345678901", aluno.getMatricula());
    }

    @Test
    void testSetMatriculaInvalida() {
        Aluno aluno = new Aluno();
        assertThrows(IllegalArgumentException.class, () -> aluno.setMatricula("matriculaInvalida"));
    }

    @Test
    void testSetCpfValido() {
        Aluno aluno = new Aluno();
        aluno.setCpf("12345678901");
        assertEquals("12345678901", aluno.getCpf());
    }

    @Test
    void testSetCpfInvalido() {
        Aluno aluno = new Aluno();
        assertThrows(IllegalArgumentException.class, () -> aluno.setCpf("cpfInvalido"));
    }

    @Test
    void testSetCpfComLetras() {
        Aluno aluno = new Aluno();
        assertThrows(IllegalArgumentException.class, () -> aluno.setCpf("abcde12345"));
    }

    @Test
    void testSetCpfComMenosDigitos() {
        Aluno aluno = new Aluno();
        assertThrows(IllegalArgumentException.class, () -> aluno.setCpf("12345"));
    }

    @Test
    void testSetCpfComMaisDigitos() {
        Aluno aluno = new Aluno();
        assertThrows(IllegalArgumentException.class, () -> aluno.setCpf("123456789012"));
    }
}
