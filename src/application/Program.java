package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import entities.Aluno;
import jdbc.AlunoJDBC;


public class Program {

	public static void main(String[] args) {
		
		try {
        	
            int opcao = 0;
            Scanner console = new Scanner(System.in);
            
            do {
            	System.out.println("####### Menu #######"
            						+ "\n1 - Cadastrar"
            						+ "\n2 - Listar"
            						+ "\n3 - Alterar"
            						+ "\n4 - Excluir"
            						+ "\n5 - Sair");
            	System.out.println("\n\tOpções:");
            	opcao = Integer.parseInt(console.nextLine());
            	
            	if (opcao == 1) {
            	
            		Aluno a = new Aluno();
            		AlunoJDBC acao = new AlunoJDBC();
            		
            		System.out.println("\n ### Cadastrar Aluno ### \n\r");
            		
            		System.out.print("Nome: ");
            		a.setNome(console.nextLine());
            		
            		System.out.print("Sexo: ");
            		a.setSexo(console.nextLine());
            	
            		System.out.print("Data de Nascimento (dd-mm-aaaa): ");
            		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            		a.setDt_nasc( LocalDate.parse(console.nextLine(), formato) );
            		
            		acao.salvar(a);
            		console.nextLine();
            		System.out.println("\n\n\n\n");
            	}
            	if(opcao == 2) {
            		
            		AlunoJDBC a = new AlunoJDBC();
            		List<Aluno> alunos = a.listar();
            		
            		System.out.println("*** Lista dos alunos ***");
            		System.out.println("ID \t Nome \t Sexo \t\t Data de Nascimento");
        			
        			for (Aluno aluno : alunos) {
        				System.out.println(aluno.getId() + " \t " + 
        								   aluno.getNome() + " \t " + 
        								   aluno.getSexo() + " \t " + 
        								   aluno.getDt_nasc());
        			}
            	}            	
            	if(opcao == 3) {
            		Aluno a = new Aluno();
            		AlunoJDBC acao = new AlunoJDBC();
            		
            		System.out.println("\n ### Alterar Aluno ### \n\r");
            		
            		System.out.print("Novo Nome: ");
            		a.setNome(console.nextLine());
            		
            		System.out.print("Novo Sexo: ");
            		a.setSexo(console.nextLine());
            	
            		System.out.print("Nova Data de Nascimento (dd-mm-aaaa): ");
            		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            		a.setDt_nasc( LocalDate.parse(console.nextLine(), formato) );
            		
            		System.out.print("Id do Aluno: ");
            		a.setId(Integer.parseInt(console.nextLine()));
            		
            		acao.alterar(a);
            		console.nextLine();
            		System.out.println("\n\n\n\n");
            	}
            	if(opcao == 4) {
            		System.out.print("Id do Aluno: ");
            		int id = Integer.parseInt(console.nextLine());
            		
            		AlunoJDBC a = new AlunoJDBC();
            		a.apagar(id);
            		
            	}
            	
            	System.out.println("\n\n");
            	
            } while(opcao != 5);
            
        } catch (Exception e){
            System.out.println("Erro: " + e);
        }
	} 
}
