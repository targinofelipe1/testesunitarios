import org.example.Aluno;
import org.example.AlunoDAO;
import org.example.AlunoService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



class AlunoServiceTest {

    @Test
    void testInserirAlunoComMatriculaExistente() {
        AlunoDAO alunoDAOMock = mock(AlunoDAO.class);
        when(alunoDAOMock.existeMatriculaAtiva(any())).thenReturn(true);

        AlunoService alunoService = new AlunoService(alunoDAOMock);
        Aluno aluno = new Aluno();
        aluno.setMatricula("12345678901"); // Matrícula válida
        assertThrows(IllegalArgumentException.class, () -> alunoService.inserirAluno(aluno));

        // Verifica se o método existeMatriculaAtiva foi chamado com a matrícula correta
        verify(alunoDAOMock, times(1)).existeMatriculaAtiva("12345678901");
    }

    @Test
    void testRemoverAluno() {
        AlunoDAO alunoDAOMock = mock(AlunoDAO.class);
        AlunoService alunoService = new AlunoService(alunoDAOMock);

        when(alunoDAOMock.recuperarAlunoPorId(1)).thenReturn(new Aluno());

        alunoService.removerAluno(1);

        verify(alunoDAOMock, times(1)).removerAluno(1);
    }

    @Test
    void testAtualizarAluno() {
        List<Aluno> alunos = new ArrayList<>();
        Aluno aluno = new Aluno();
        aluno.setId(1);
        aluno.setMatricula("12345678901");
        aluno.setCpf("12345678901");
        alunos.add(aluno);

        AlunoDAO alunoDAO = new AlunoDAO(alunos);

        Aluno alunoAtualizado = new Aluno();
        alunoAtualizado.setId(1);
        alunoAtualizado.setMatricula("98765432105");
        alunoAtualizado.setCpf("98765432105");

        alunoDAO.atualizarAluno(alunoAtualizado);

        assertEquals(alunoAtualizado, alunoDAO.recuperarAlunoPorId(1));
    }

    @Test
    void testInserirAlunoValido() {
        AlunoDAO alunoDAOMock = mock(AlunoDAO.class);
        when(alunoDAOMock.existeMatriculaAtiva(any())).thenReturn(false);

        AlunoService alunoService = new AlunoService(alunoDAOMock);
        Aluno aluno = new Aluno();
        aluno.setMatricula("12345678901");
        aluno.setCpf("12345678901");
        aluno.setIdade(20);

        assertDoesNotThrow(() -> alunoService.inserirAluno(aluno));
    }

    @Test
    void testListarTodosAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        Aluno aluno1 = new Aluno();
        aluno1.setId(1);
        aluno1.setMatricula("12345678901");
        aluno1.setCpf("12345678901");

        Aluno aluno2 = new Aluno();
        aluno2.setId(2);
        aluno2.setMatricula("98765432105");
        aluno2.setCpf("98765432105");

        alunos.add(aluno1);
        alunos.add(aluno2);

        AlunoDAO alunoDAO = new AlunoDAO(alunos);

        assertEquals(alunos, alunoDAO.listarTodosAlunos());
    }
}


