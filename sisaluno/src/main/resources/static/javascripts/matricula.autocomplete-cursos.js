Sisaluno = Sisaluno || {};

Sisaluno.Autocomplete = (function() {
	
	function Autocomplete() {
		this.skuInput = $('.js-sku-curso-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-curso').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
	}
	
	Autocomplete.prototype.iniciar = function() {
		var options = {
			url: function(sku) {
				return '/sisaluno/cursos?sku=' + sku;
			},
			getValue: 'sku',
			minCharNumber: 3,
			requestDelay: 300,
			ajaxSettings: {
				contentType: 'application/json'
			}
			/*template: {
				type: 'custom',
				method: function(sku, curso) {
//					curso.valorFormatado = Sisaluno.formatarMoeda(curso.tfm);
					return this.template(curso);
				}.bind(this)
			}*/
		};
		
		this.skuInput.easyAutocomplete(options);
	}
	
	return Autocomplete
	
}());

$(function() {
	
	var autocomplete = new Sisaluno.Autocomplete();
	autocomplete.iniciar();
	
})

