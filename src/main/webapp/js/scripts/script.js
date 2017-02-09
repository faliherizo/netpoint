$(function(){
	$('.date').datepicker({dateFormat: 'dd/mm/yy', changeYear:true, showButtonPanel: true, yearRange : '1940:2030', monthNames: ['Janvier','F&eaucte;vrier','Mars','Avril','Mai','Juin','Juillet','Août','Septembre','Octobre','Novembre','D&eacute;cembre'],
		dayNamesMin: ['Di', 'Lu', 'Ma', 'Me', 'Je', 'Ve', 'Sa'], firstDay: 1});
	$('#date').datepicker({dateFormat: 'dd/mm/yy', changeYear:true, showButtonPanel: true, yearRange : '1940:2030', monthNames: ['Janvier','F&eacute;vrier','Mars','Avril','Mai','Juin','Juillet','Août','Septembre','Octobre','Novembre','D&eacute;cembre'],
		dayNamesMin: ['Di', 'Lu', 'Ma', 'Me', 'Je', 'Ve', 'Sa'], firstDay: 1});
	$('#date_fin').datepicker({dateFormat: 'dd/mm/yy', changeYear:true, showButtonPanel: true, yearRange : '1940:2030', monthNames: ['Janvier','F&eacute;vrier','Mars','Avril','Mai','Juin','Juillet','Août','Septembre','Octobre','Novembre','D&eacute;cembre'],
		dayNamesMin: ['Di', 'Lu', 'Ma', 'Me', 'Je', 'Ve', 'Sa'], firstDay: 1});
	$('.date_fin').datepicker({dateFormat: 'dd/mm/yy', changeYear:true, showButtonPanel: true, yearRange : '1940:2030', monthNames: ['Janvier','F&eacute;vrier','Mars','Avril','Mai','Juin','Juillet','Août','Septembre','Octobre','Novembre','D&eacute;cembre'],
		dayNamesMin: ['Di', 'Lu', 'Ma', 'Me', 'Je', 'Ve', 'Sa'], firstDay: 1});
	$('#date_debut').datepicker({dateFormat: 'dd/mm/yy', changeYear:true, showButtonPanel: true, yearRange : '1940:2030', monthNames: ['Janvier','F&eacute;vrier','Mars','Avril','Mai','Juin','Juillet','Août','Septembre','Octobre','Novembre','D&eacute;cembre'],
		dayNamesMin: ['Di', 'Lu', 'Ma', 'Me', 'Je', 'Ve', 'Sa'], firstDay: 1});
	$('.date_debut').datepicker({dateFormat: 'dd/mm/yy', changeYear:true, showButtonPanel: true, yearRange : '1940:2030', monthNames: ['Janvier','F&eacute;vrier','Mars','Avril','Mai','Juin','Juillet','Août','Septembre','Octobre','Novembre','D&eacute;cembre'],
		dayNamesMin: ['Di', 'Lu', 'Ma', 'Me', 'Je', 'Ve', 'Sa'], firstDay: 1});
	//Validation date
	$('.dateerror').hide();
	$('#submitdate').click(function(event){
		var data=$('.date').val();
		if(validate_date(data)){
			$('.dateerror').hide();
		}
		else{
			$('.dateerror').show();
			event.preventDefault();
		}
	});
	
	//Pagination table
	$('table.paginated').each(function(){
		var currentPage = 0;
		var numberPage=8;
		var $table=$(this);
		var repaginate = function(){
			$table.find('tbody tr').hide().slice(currentPage*numberPage,(currentPage+1)*numberPage).show();
		};
		repaginate();
		var numRows=$table.find('tbody tr').length;
		var numPages=Math.ceil(numRows/numberPage);
		if(numPages>=1){
			var $pager=$('<div class="pager">Pages :</div>');
		}
		else{
			var $pager=$('<div class="pager"></div>');
		}
		for (var page=0; page<numPages;page++){
			$('<span class="page-number"></span>').text(page+1)
			.bind('click',{newPage: page},function(event){
				currentPage=event.data['newPage'];
				repaginate();
				$(this).addClass('activePage').siblings().removeClass('activePage');
			}).appendTo($pager).addClass('clickable');
		}
		$pager.insertAfter($table).find('span.page-number:first').addClass('clickable').addClass('activePage');
	});
});


function validate_date(date){
	var pattern = new RegExp(/\b\d{1,2}[\/-]\d{1,2}[\/-]\d{4}\b/);
	return pattern.test(date);
}
