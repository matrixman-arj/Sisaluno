package br.mil.eb.decex.sisaluno.storage;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;


public interface FotoStorage {
			
	public String salvarTemporariamente(MultipartFile[] files);

	public byte[] recuperarFotoTemporaria(String nome);

	public void salvar(String string);	

	public byte[] recuperar(String foto);
	
	public byte[] recuperarThumbnail(String fotoUsuario);	

	public void excluir(String foto);

//	public String getUrl(String foto);

	default String renomearArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "_" + nomeOriginal;
	}

		
}
