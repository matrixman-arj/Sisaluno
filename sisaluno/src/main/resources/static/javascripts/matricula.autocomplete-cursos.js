Sisaluno = Sisaluno || {};

Sisaluno.Autocomplete = (function() {
	
	function Autocomplete() {
		this.categoriaInput = $('.js-categoria-curso-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-curso').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
	}
	
	Autocomplete.prototype.iniciar = function() {
		var options = {
			url: function(categoria) {
				return '/sisaluno/cursos?categoria=' + categoria.descricao;
			},
			getValue: 'categoria',
			minCharNumber: 3,
			requestDelay: 300,
			ajaxSettings: {
				contentType: 'application/json'
			},
			template: {
				type: 'custom',
				method: function(categoria, curso) {
//					curso.valorFormatado = Sisaluno.formatarMoeda(curso.tfm);
					return this.template(curso);
				}.bind(this)
			}
		};
		
		this.categoriaInput.easyAutocomplete(options);
	}
	
	return Autocomplete
	
}());

$(function() {
	
	var autocomplete = new Sisaluno.Autocomplete();
	autocomplete.iniciar();
	
})

