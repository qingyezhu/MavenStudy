/**
 * 
 */

var queryWaitZIndex = 9999,
	urlObj = {
		queryUrl: '/hello/list.json'
	},
	$tipMessage = null,
	$maskLayer = null,
	$table = null;
$(function(){
	//消息提示框
	$tipMessage = createTipMsg('tipMessageId');
	//遮罩层对象
	$maskLayer = createMask('maskLayerId', queryWaitZIndex);
	
	$table = $('#tableId').BambooDataTable({
		'id': 'tableId',
		'url': urlObj.queryUrl,
		'width': 1200,
		'height': 400,
		'getTotal': function(ret){
			return ret['count'];
		},
		'columns': [{
			'field': 'id',
			'title': '编号 ',
			'width': 60
		},{
			'field': 'name',
			'title': '姓名',
			'width': 120
		},{
			'field': 'age',
			'title': '年龄',
			'width': 100
		},{
			'field': 'class',
			'title': '班级',
			'width': 80
		},{
			'field': 'score',
			'title': '成绩',
			'width': 120,
			'render': function(index, val, row){
				var _color = 'red';
				if(val >= 90){
					_color = 'blue';
				}else if(val > 75){
					_color = 'green';
				}else if(val > 60){
					_color = 'black';
				}
				return '<font color={0}>{1}</font>'.format(_color, val);
			}
		}]
	});
	$table.BambooDataTable('reload');
});