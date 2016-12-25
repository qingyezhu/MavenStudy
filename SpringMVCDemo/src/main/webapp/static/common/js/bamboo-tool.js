/**
*公有方法
*/

//增加元素
Array.prototype.uniquepush = function(obj, field){
	for(var i = 0, len = this.length;i < len;i ++){
		if(obj[field] == this[i][field]){
			return false;
		}
	}
	this.push(obj);
	return true;
};

//删除元素
Array.prototype.remove = function(obj, field){
	for(var i = 0, len = this.length;i < len;i ++){
		if(obj[field] == this[i][field]){
			this.splice(i, 1);
			return true;
		}
	}
	return false;
};

//是否包含元素
Array.prototype.contains = function(obj){
	for(var i = 0, len = this.length;i < len;i ++){
		if(this[i] == obj){
			return true;
		}
	}	
	return false;
}

//格式化输入字符串
//用法："hello {0}".format('world');返回'hello world'
String.prototype.format = function(){
	var args = arguments;
	return this.replace(/\{(\d+)\}/g, function(s, i){
		return args[i];
	});
}

String.prototype.endWith = function(str){
	if(str == null || str.length == 0 || this.length == 0 || str.length > this.length){
		return false;
	}
	//substring(start[, end])	
	if(this.substring(this.length - str.length) == str){
		return true;
	}
	return false;
}

String.prototype.startWith = function(str){
	if(str == null || str.length == 0 || this.length == 0 || str.length > this.length){
		return false;
	}
	//substr(start[, length])
	if(this.substr(0, str.length) == str){
		return true;
	}
	return false;
}

String.prototype.tirm = function(flag){
	var ret = this.replace(/(^\s+)|(\s+$)/g, '');
	if(flag){
		ret = ret.replace(/\s+/g, '');
	}
	return ret;
}

Date.prototype.format = function(fmt){
	var obj = {
		'M+': this.getMonth() + 1, //月份
		'd+': this.getDate(), //日
		'h+': this.getHours(), //小时
		'm+': this.getMinutes(), //分
		's+': this.getSeconds(), //秒
		'q+': Math.floor((this.getMonth() + 3) / 3), //季度
		'S': this.getMilliseconds()//毫秒
	};
	if(/(y+)/.test(fmt)){
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}

	for(var k in obj){
		if(new RegExp('(' + k + ')').test(fmt)){
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? obj[k] : (('00' + obj[k]).substr(('' + obj[k]).length)));
		}
	}

	return fmt;
}
/**
 * 消息提示框
 */
;(function(){
	$.fn.BambooTipMessage = function(options){
		var defaults = {
			msg: '提示信息',
			tipMessageCssName: 'bamboo-tip-message',
			contentCssName: 'bamboo-tip-message-content',
			msgCssName: 'bamboo-tip-message-msg',
			autoOpen: false,
			zIndex: 999
		};

		var settings = $.extend({}, defaults, options);

		var methods = {
			init: function(){
				//初始化方法

				//创建消息提示
				methods.createTipMessage.call(this);

				//是否打开消息提示
				if(settings.autoOpen){
					//打开消息提示
					methods.openTipMessage.call(this);
				}
				return this;
			},
			createTipMessage: function(){
				//创建消息提示
				var that = this;

				that.prop('class', settings.tipMessageCssName).css('zIndex', settings.zIndex);

				var $msg = $('<span></span>').prop('class', settings.msgCssName).html(settings.msg);

				var $content = $('<div></div>').prop('class', settings.contentCssName);

				$msg.appendTo($content);
				that.append($content).css('display', 'none');

				return that;
			},
			openTipMessage: function(msg){
				//打开消息提示
				var that = this;
				$.fn.BambooTipMessage.showTip.call(that, msg);
				return that;

			},
			closeTipMessage: function(){
				//关闭消息提示
				var that = this;
				$.fn.BambooTipMessage.hideTip.call(that);
				return that;
			}
		};

		var method = arguments[0];
		if(methods[method]){
			method = methods[method];
			arguments = Array.prototype.slice.call(arguments, 1);
		}else if(isObjectType(method) || !method){
			method = methods['init'];
		}else{
			$.error('method ' + method + ' does not exist on jQuery.tipMessage');
			return this;
		}
		return method.apply(this, arguments);
	}

	$.fn.BambooTipMessage.showTip = function(msg){
		//打开消息提示
		var that = this;

		//重置消息
		msg && that.find('span').html(msg);

		var height = that.css('height');

		that.css({'opacity': 1, 'display': 'block', 'top': '-' + height.substr(0, height.length - 2) + 'px'});

		that.animate({top: 0}, 'normal');
		return that;
	}

	$.fn.BambooTipMessage.hideTip = function(){
		//关闭消息提示
		var that = this;

		var height = that.css('height');

		that.animate({'opacity': 0, 'top': '-' + height.substr(0, height.length - 2) + 'px'}, 'normal', null, function(){
			that.css({'display': 'none'});
		});
		return that;
	}

})(jQuery);

/**
 * 遮罩层
 */
;(function($){
	$.fn.BambooMaskLayer = function(options){
		var defaults = {
			msg: '请稍候',
			img: '/static/common/images/waiting.gif',
			body: $(document.body),
			maskLayerCssName: 'bamboo-mask-layer',
			conentLayerCssName: 'bamboo-mask-layer-conent',
			msgMaskLayerCssName: 'bamboo-mask-layer-msg',
			imgMaskLayerCssName: 'bamboo-mask-layer-img',
			autoOpen: false,
			zIndex: 999
		};
		
		var settings = $.extend({}, defaults, options);

		var methods = {
			init: function(){
				//初始化方法
				var that = this;

				//创建遮罩层
				methods.createMaskLayer.call(that);

				//是否打开遮罩层
				var autoOpen = settings.autoOpen;
				if(autoOpen){
					//打开遮罩层
					methods.openMask.call(that);

				}
				return that;
			},
			createMaskLayer: function(){
				//创建遮罩层方法

				var that = this;
				that.prop('class', settings.maskLayerCssName).css('zIndex', settings.zIndex);
				
				var $msg = $('<span></span>').prop('class', settings.msgMaskLayerCssName).html(settings.msg);

				var $img = $('<img/>').prop('class', settings.imgMaskLayerCssName).prop('src', settings.img);

				//内容在可见屏幕的中间
				var $content = $('<div></div>').prop('class', settings.conentLayerCssName).css({
					left: (Math.max(document.body.clientWidth, document.documentElement.clientWidth) - 200) / 2 + 'px', 
					top: (Math.max(document.body.clientHeight, document.documentElement.clientHeight) - 50) / 2 + 'px'});

				$img.appendTo($content);
				$msg.appendTo($content);
				that.append($content).css('display', 'none');

				return that;
			},
			openMask: function(msg){
				//打开遮罩层方法

				var that = this;
				$.fn.BambooMaskLayer.showMask.call(that, msg);
				return that;
			},
			closeMask: function(){
				//关闭遮罩层方法

				var that = this;
				$.fn.BambooMaskLayer.hideMask.call(that);
				return that;
			}

		};

		var method = arguments[0];

		if(methods[method]){
			method = methods[method];
			arguments = Array.prototype.slice.call(arguments, 1);
		}else if(isObjectType(method) || !method){
			method = methods['init'];
		}else{
			$.error('method ' + method + ' does not exist on jQuery.MaskLayer');
			return this;
		}

		return method.apply(this, arguments);
	};

	$.fn.BambooMaskLayer.showMask = function(msg){
		//重写遮罩层显示方法
		var that = this;

		//重置遮罩提示
		msg && that.find('span').html(msg);

		var height = that.css('height');
		var _scrollHeight = Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
		var _scrollWidth = Math.max(document.body.scrollWidth, document.documentElement.scrollWidth);

		that.css({'opacity': .8, 'display': 'block', 'top': '-' + height.substr(0, height.length - 2) + 'px', 'width': _scrollWidth + 'px', 'height': _scrollHeight + 'px'});

		that.animate({top: 0}, 'normal');
		return that;
	}
	$.fn.BambooMaskLayer.hideMask = function(){
		//重写遮罩层隐藏方法
		var that = this;

		var height = that.css('height');

		that.animate({'opacity': 0, 'top': height.substr(0, height.length - 2) + 'px'}, 'normal', null, function(){
			that.css('display', 'none');
		});
		return that;
	}


})(jQuery);

/**
*分页
*/
;(function($){
	$.fn.BambooPaginator = function(options){
		var methods = {
			init: function(){
				//分页组件初始化
				var that = this;

				//更新参数
				methods.setOptions.call(that, $.fn.BambooPaginator.defaultOptions, options);
				//扩展JS
				methods.extendBambooJQuery.call(that);
				if(methods.getOption.call(that, 'initRender')){
					//渲染页面
					methods.renderPage.call(that);
				}
				if(methods.getOption.call(that, 'initFire')){
					//自动触发事件
					methods.fireChangeEven.call(that, that.options.currentPage, 'init');
				}
				return that;
			},
			setOptions: function(defaultOptions, options){
				//设置配置参数
				var that = this;

				that.options = $.extend({}, defaultOptions, options);

				return that;
			},
			getOption: function(field){
				//获取某个参数的值
				var that = this;
				return that.options[field];
			},
			extendBambooJQuery: function(){
				//扩展此次需要的JS
				var that = this;

				$.fn.BambooPaginatorHtml = function(){
					return $('<p>').append(this).html();
				}

				return that;
			},
			renderPage: function(){
				//渲染分页组件页面
				var that = this;
				
				//验证参数
				methods.verify.call(that);
				//刷新Html
				methods.renderPageHtml.call(that);
				//刷新状态
				methods.renderPageStatus.call(that);
				//绑定事件
				methods.bindChangeEvents.call(that);

				return that;
			},
			verify: function(){
				//验证参数是否是数字以及初始化总页数
				var that = this,
					totalCounts = methods.getOption.call(that, 'totalCounts'),
					pageSize = methods.getOption.call(that, 'pageSize'),
					currentPage = methods.getOption.call(that, 'currentPage');

				if(!methods.isNumber(totalCounts)){
					throw new Error('type error: totalCounts');
				}
				if(!methods.isNumber(pageSize)){
					throw new Error('type error: pageSize');
				}
				if(!methods.isNumber(currentPage)){
					throw new Error('type error: currentPage');
				}
				if(totalCounts >= 0 && pageSize > 0){
					methods.setOptions.call(that, that.options, {'totalPages': Math.ceil(totalCounts / pageSize)});
				}

				return that;
			},
			isNumber: function(val){
				//判断是否是数字
				return isNumberType(val);
			},
			renderPageHtml: function(){
				//渲染分页组件代码
				var	that = this,
					html = [],
					pageArr = methods.getVisiblePageArr.call(that),
					currentPage = methods.getOption.call(that, 'currentPage'),
					totalPages = methods.getOption.call(that, 'totalPages');

				for(var index = 0, len = pageArr.length; index < len; index ++){
					html.push(methods.createPageHtml.call(that, 'page', pageArr[index]));
				}

				html.unshift(methods.createPageHtml.call(that, 'prev', currentPage - 1));
				html.unshift(methods.createPageHtml.call(that, 'first', 1));
				html.push(methods.createPageHtml.call(that, 'next', currentPage + 1));
				html.push(methods.createPageHtml.call(that, 'last', totalPages));

				that.html(html.join(''));

				return that;
			},
			getVisiblePageArr: function(){
				//获取当前可见页的数字数组
				var that = this,
					pageArr = [],
					visiblePage = methods.getOption.call(that, 'visiblePage'),
					currentPage = methods.getOption.call(that, 'currentPage'),
					totalPages = methods.getOption.call(that, 'totalPages');

				if(visiblePage > totalPages){
					visiblePage = totalPages;
				}

				var half = Math.floor(visiblePage / 2),
					start = currentPage - half + 1 - (visiblePage % 2),
					end = currentPage + half;

				if(start < 1){
					start = 1;
					end = visiblePage;
				}

				if(end > totalPages){
					end = totalPages;
					start = totalPages - visiblePage + 1;
				}

				var index = start;
				while(index <= end){
					pageArr.push(index);
					index ++;
				}

				return pageArr;
			},
			createPageHtml: function(pageMark, pageIndex){
				//创建每一页的代码
				var that = this,
					page = methods.getOption.call(that, pageMark).replace(/{{page}}/g, pageIndex);
				return $(page).attr({
					'bamboo-role': pageMark,
					'bamboo-data': pageIndex
				}).BambooPaginatorHtml();
			},
			renderPageStatus: function(){
				//渲染页面中的每一页的样式
				var that = this,
					currentPage = methods.getOption.call(that, 'currentPage'),
					totalPages = methods.getOption.call(that, 'totalPages'),
					activeClass = methods.getOption.call(that, 'activeClass'),
					disabledClass = methods.getOption.call(that, 'disabledClass');

				if(currentPage == 1){
					that.find('[bamboo-role=first]').addClass(disabledClass);
					that.find('[bamboo-role=prev]').addClass(disabledClass);
				}

				if(currentPage == totalPages){
					that.find('[bamboo-role=last]').addClass(disabledClass);
					that.find('[bamboo-role=next]').addClass(disabledClass);
				}

				that.find('[bamboo-role=page]').removeClass(activeClass);
				that.find('[bamboo-role=page][bamboo-data=' + currentPage + ']').addClass(activeClass);

				return that;
			},
			bindChangeEvents: function(){
				//绑定更改页事件
				var that = this;

				that.off('click', '[bamboo-role]').on('click', '[bamboo-role]', function(){
					var $e = $(this);
					if($e.hasClass(methods.getOption.call(that, 'disabledClass')) || $e.hasClass(methods.getOption.call(that, 'activeClass'))){
						return ;
					}

					var pageIndex = +$e.attr('bamboo-data');
					if(methods.fireChangeEven.call(that, pageIndex, 'change')){
						methods.switchPage.call(that, pageIndex);
					}

				});

				return that;
			},
			fireChangeEven: function(pageIndex, type){
				//自动触发type更改事件
				var that = this,
					onPageChange = methods.getOption.call(that, 'onPageChange');

				return (!isFunctionType(onPageChange)) || (onPageChange(pageIndex, type) !== false);
			},
			switchPage: function(pageIndex){
				//跳转到pageIndex页
				var that = this;

				//设置当前页
				methods.setOptions.call(that, that.options, {'currentPage': pageIndex});

				if(methods.getOption.call(that, 'innerRender')){
					//重新刷新页面
					methods.renderPage.call(that);
				}
				return that;
			},
			updateOptions: function(_options){
				//更新配置参数，并进行刷新页面
				var that = this;
				
				//更新参数
				methods.setOptions.call(that, that.options, _options);
				//重新刷新页面
				methods.renderPage.call(that);

				return that;
			}
		};
		
		var action = arguments[0], 
			method = null;
		if(methods[action]){
			method = methods[action];
			arguments = Array.prototype.slice.call(arguments, 1);

		}else if(isObjectType(action) || !action){
			method = methods['init'];
		}else{
			$.error('method ' + action + ' does not exists on jQuery.BambooPaginator');
		}
		var ret = method.apply(this, arguments);

		if('getOption' == action){
			//供外部调用
			return ret;
		}
		return this;
	};

	$.fn.BambooPaginator.defaultOptions = {
		initRender: true,//是否初始化渲染
		initFire: false,//是否初始化出发change事件
		visiblePage: 5, //可见的页的个数
		totalPages: 0,//总页数
		totalCounts: 0,//总条目数
		pageSize: 10,//每页的条目数
		currentPage: 1,//当前页
		page: '<li class=\"page\"><a href=\"javascript:;\">{{page}}</a></li>',
		first: '<li class=\"first\"><a href=\"javascript:;\">首页</a></li>',
		last: '<li class=\"last\"><a href=\"javascript:;\">尾页</a></li>',
		prev: '<li class=\"prev\"><a href=\"javascript:;\">上一页</a></li>',
		next: '<li class=\"next\"><a href=\"javascript:;\">下一页</a></li>',
		activeClass: 'active',
		disabledClass: 'disabled',
		innerRender: true,//当页面跳转时，是否由内部进行页面刷新；其中updateOptions方法再更新配置参数时，也会进行页面刷新
		onPageChange: null
	};
})(jQuery);

/*
*表格
*/
;(function($){
	$.fn.BambooDataTable = function(options){
		var methods = {
			init: function(){
				//表格初始化
				var that = this;

				//更新参数
				methods.setOptions.call(that, $.fn.BambooDataTable.defaultOptions, options);
				//处理初始化参数
				methods.initParam.call(that);
				//包装包括分页工具的添加
				methods.initWrap.call(that);
				//刷新工具栏
				methods.renderToolbar.call(that);
				//刷新表头
				methods.renderHead.call(that);
				if(methods.getOption.call(that, 'initWrap')){
					//刷新表体
					methods.renderBody.call(that);
				}
				//初始化事件
				methods.renderEven.call(that);

				return that;
			},
			setOptions: function(defaultOptions, options){
				//设置配置参数
				var that = this;

				that.options = $.extend({}, defaultOptions, options);
				
				return that;
			},
			getOption: function(field){
				//获取某个参数值
				var that = this;
				return that.options[field];
			},
			initWrap: function(){
				var that = this,
					tableId = methods.getOption.call(that, 'id'),
					toolbarId = methods.getToolbarId.call(that),
					paginatorId = methods.getPaginatorId.call(that),
					wrapId = '{0}_wrap'.format(tableId),
					toolbarArr = methods.getOption.call(that, 'toolbar');

				that.addClass('bambooDataTable');
				that.css({
					'width': methods.getOption.call(that, 'width')
				});

				if(methods.exitsToolbar.call(that)){
					//添加工具栏
					that.before('<div id=\"{0}\" style=\"width: {1}px; text-align: left;\" ></div>'.format(toolbarId, methods.getOption.call(that, 'width')));
				}

				if(methods.getOption.call(that, 'isPaginaion')){
					//添加分页
					that.after('<div style=\"width: {0}px; text-align: {1};\"><ul id=\"{2}\" class=\"bamboo-pagination\"></ul></div>'.format(methods.getOption.call(that, 'width'), methods.getOption.call(that, 'paginationAlign'), paginatorId));
				}
				that.wrap('<div id=\"{0}\" style=\"width: {1}px; height: {2}px;\"></div>'.format(wrapId, methods.getOption.call(that, 'width'), methods.getOption.call(that, 'height')));

				return that;
			},
			initParam: function(){
				var that = this,
					currentPageFieldName = methods.getOption.call(that, 'currentPageFieldName'),
					pageSizeFieldName = methods.getOption.call(that, 'pageSizeFieldName');

				methods.setOptions.call(that, that.options, {
					'currentPage': methods.getOption.call(that, currentPageFieldName),
					'pageSize': methods.getOption.call(that, pageSizeFieldName)
				})

				return that;
			},
			getPaginatorId: function(){
				//获取分页Id
					var that = this,
					tableId = methods.getOption.call(that, 'id'),
					toolbarId = '{0}_pagination'.format(tableId);
				return toolbarId;
			},
			getToolbarId: function(){
				//获取工具栏Id
				var that = this,
					tableId = methods.getOption.call(that, 'id'),
					toolbarId = '{0}_toolbar'.format(tableId);
				return toolbarId;
			},
			exitsToolbar: function(){
				//是否存在工具栏
				var that = this,
					toolbarArr = methods.getOption.call(that, 'toolbar');
				if(isArrayType(toolbarArr) && toolbarArr.length > 0){
					return true;
				}
				return false;
			},
			renderToolbar: function(){
				//刷新工具栏
				var that = this;
				if(methods.exitsToolbar.call(that)){
					//当存在工具栏时
					var toolbarArr = methods.getOption.call(that, 'toolbar'),
						toolbarId = methods.getToolbarId.call(that),
						handlerObj = {};

					var htmlArr = [];
					$.each(toolbarArr, function(index, toolbar){
						var toolbarHtml = '<a href="javascript:;" class=\"bamboo-btn bamboo-toolbar-btn\" bammboo-toolbar-name=\"{0}\">{1}</a>'.format(toolbar['name'], toolbar['text']);
						htmlArr.push(toolbarHtml);

						handlerObj[toolbar['name']] = toolbar['handler']; 
					});

					$('#{0}'.format(toolbarId)).html(htmlArr.join('')).on('click', 'a', function(){
						var handler = handlerObj[this.getAttribute('bammboo-toolbar-name')];
						if(isFunctionType(handler)){
							handler.call(null);
						}
						return false;
					});
				}
			},
			renderHead: function(){
				//刷新表头
				var that = this,
					columnArr = methods.getOption.call(that, 'columns');

				var headArr = [];
				$.each(columnArr, function(index, column){
					headArr.push('<th width=\"{0}px\">{1}</th>'.format(column['width'], column['title']));
				});
				
				that.html('').html('<thead><tr>{0}</tr></thead>'.format(headArr.join('')));

				return that;
			},
			renderBody: function(){
				//刷新表体
				var that = this,
					paramObj = methods.getOption.call(that, 'queryParamObj') || {};

				//更新分页相关参数
				paramObj[methods.getOption.call(that, 'currentPageFieldName')] = methods.getOption.call(that, 'currentPage');
				paramObj[methods.getOption.call(that, 'pageSizeFieldName')] = methods.getOption.call(that, 'pageSize');

				var options = {
					'url': methods.getOption.call(that, 'url'),
					'data': paramObj,
					'dataType': methods.getOption.call(that, 'dataType'),
					'callback': function(ret){
						methods.initTable.call(that, ret);
					}
				};
				ajaxJQuery(options);

				return that;
			},
			initTable: function(ret){
				//初始化表格数据
				var that = this,
					columnArr = methods.getOption.call(that, 'columns'),
					columnLength = columnArr.length,
					htmlArr = [],
					data = methods.getOption.call(that, 'getData')(ret),
					isTreeData = methods.getOption.call(that, 'isTreeData'),
					getTreeColumnName = methods.getOption.call(that, 'getTreeColumnName'),
					treeColumnName = null;
				if(isFunctionType(getTreeColumnName)){
					//获取树形数组中的字数组名称
					treeColumnName = getTreeColumnName.call(null);
				}

				if(!(isTreeData && !isNull(treeColumnName))){
					isTreeData = false;
				}

				that.find('tbody tr').remove();
				$.each(data, function(rowIndex, row){
					var _rowHtmlArr = [];
					$.each(columnArr, function(columnIndex, column){
						var _columnHtml = methods._getColumnHtml.call(that, rowIndex, row, columnIndex, column);
						_rowHtmlArr.push(_columnHtml);
					});

					//奇偶换行
					var classStyle = methods._getEvenOrOddClass.call(that);

					if(isTreeData){
						classStyle = 'class=bamboo-table-parent';
					}
					var _rowHtml = '<tr {0}>{1}</tr>'.format(classStyle, _rowHtmlArr.join(''));
					htmlArr.push(_rowHtml);

					//是否是树形数据
					if(isTreeData){
						var _childHtml = methods._getChildHtml.call(that, columnArr, rowIndex, row, treeColumnName);
						if(isStringType(_childHtml) && _childHtml.length > 0){
							var _childRowHtml = '<tr><td colspan={0}><table><tbody>{1}</tbody></table></td></tr>'.format(columnLength, _childHtml);
							htmlArr.push(_childHtml);
						}
					}
				});

				//checkbox的事件委托关联操作
				if(isTreeData){
					that.on('click', 'input[name=bammbooCheckbox]', function(){
						var _checked = this.checked;
						console.log(_checked);
					});
				}

				that.append(htmlArr.join(''));
				methods.updatePaginator.call(that, methods.getOption.call(that, 'getTotal')(ret));
				methods.setOptions.call(that, that.options, {
					'data': data
				});

				//提示信息
				showMsg('表格初始化完成！');

				return that;
			},
			_getEvenOrOddClass: function(rowIndex){
				//获取奇偶行换色类
				var that = this;
				return ((rowIndex & 1) == 0) && 'class=bamboo-even' || 'class=bamboo-odd';
			},
			_getChildHtml: function(columnArr, rowIndex, row, columnName){
				var that = this,
					childData = row[columnName] || [],
					childHtmlArr = [];
				$.each(childData, function(childRowIndex, childRow){
					var _rowHtmlArr = [];
					$.each(columnArr, function(childColumnIndex, childColumn){
						var _columnHtml = methods._getColumnHtml.call(that, childRowIndex, childRow, childColumnIndex, childColumn);
						_rowHtmlArr.push(_columnHtml);
					});
					//奇偶换行
					var classStyle = methods._getEvenOrOddClass.call(that);
					var _rowHtml = '<tr {0}>{1}</tr>'.format(classStyle, _rowHtmlArr.join(''));
					childHtmlArr.push(_rowHtml);
				});
				return childHtmlArr.join('');
			},
			_getColumnHtml: function(rowIndex, row, columnIndex, column){
				//获取列html代码
				var that = this,
					title = column['field'],
					val = row[title],
					attr = column['attr'],
					data = row[attr],
					render = column['render'],
					align = column['align'];
				if(render){
					val = render.call(null, rowIndex, val, row);
				}
				if('bammbooRadio' == title){
					val = '<input type=\"{0}\" name=\"{1}\" data=\"{2}\"/>'.format('radio', title, data);
				}else if('bammbooCheckbox' == title){
					val = '<input type=\"{0}\" name=\"{1}\" data=\"{2}\"/>'.format('checkbox', title, data);
				}

				var alignHtml = align&&'align=\"{0}\"'.format(align)||'';
				return '<td {0}>{1}</td>'.format(alignHtml, val);
			},
			updatePaginator: function(totalCounts){
				//刷新分页
				var that = this,
				    $paginator = methods.getOption.call(that, 'paginator');

				if(methods.getOption.call(that, 'isPaginaion')){
					if(!$paginator){
						var paginatorId = methods.getPaginatorId.call(that);
						$paginator = $('#{0}'.format(paginatorId)).BambooPaginator({
							'initRender': false,
							'initFire': false,
							'innerRender': false,
							'pageSize': methods.getOption.call(that, 'pageSize'),
							'visiblePage': methods.getOption.call(that, 'visiblePage'),
							'onPageChange': function(currentPage){
								//更新表格当前页
								methods.setOptions.call(that, that.options, {
									'currentPage': currentPage
								});
								//更新表格当前数据
								methods.renderBody.call(that);
								return true;
							}
						});
					}
					var paramObj = {
						'totalCounts': totalCounts,
						'currentPage': methods.getOption.call(that, 'currentPage')
					};
					$paginator.BambooPaginator('updateOptions', paramObj);
				}

				return that;
			},
			renderEven: function(){
				//刷新事件
				var that = this,
					delegateEvenHref = methods.getOption.call(that, 'delegateEvenHref');
				if(isFunctionType(delegateEvenHref)){
					that.on('click', 'a', function(){
						delegateEvenHref.call(this);
					});
				}
				return that;
			},
			reload: function(queryParamObj, currentPage){
				//重新加载查询
				var that = this,
					_options = {};

				queryParamObj && (_options['queryParamObj'] = queryParamObj);
				currentPage && (_options['currentPage'] = currentPage);
				//更新参数
				methods.setOptions.call(that, that.options, _options);
				//重新查询列表
				methods.renderBody.call(that);

				return that;
			},
			selectedAllRows: function(flag){
				var that = this,
					_getColumn = methods._getColumn,
					$rows = that.find('tbody tr'),
					selectedAllFlag = false;
				if(flag){
					selectedAllFlag = methods._existAllSelectedRows.call(that);
				}
				
				$.each($rows, function(index, row){
					var _$row = $(row),
						_$column = null;
					if((_$column = _getColumn.call(that, _$row, 'bammbooRadio')) || (_$column = _getColumn.call(that, _$row, 'bammbooCheckbox'))){
						var _flag = !_$column.prop('checked');//取反
						if(flag){
							//当全选时，当没有全选，则全选，否则全不选
							_flag = !(flag & selectedAllFlag);
						}
						_$column.prop('checked', _flag);
					}
				});

				return that;
			},
			_existAllSelectedRows: function(){
				var that = this,
					_getColumn = methods._getColumn,
					$rows = that.find('tbody tr'),
					flag = true;
				for(var i = 0, len = $rows.length; flag && (i < len);i ++){
					var _$row = $($rows[i]);
					if(!(_getColumn.call(that, _$row, 'bammbooRadio', true) || _getColumn.call(that, _$row, 'bammbooCheckbox', true))){
						flag = false;
					}
				}
				return flag;
			},
			getSelectedRows: function(){
				//树形表格，返回的是checkbox或radio的设置的共享的数据，否则返回的是当前行数据
				var that = this,
					isTreeData = methods.getOption.call(that, 'isTreeData'),
					_getColumn = methods._getColumn,
					$column = null,
					$rows = that.find('tbody tr'),
					_data = methods.getOption.call(that, 'data'),
					_arr = [];

				$.each($rows, function(index, row){
					var _$row = $(row);
					if(($column = _getColumn.call(that, _$row, 'bammbooRadio', true)) || ($column = _getColumn.call(that, _$row, 'bammbooCheckbox', true))){
						if(isTreeData){
							_arr.push($column.attr('data'));
						}else{
							_arr.push(_data[index]);
						}
					}
				});
				
				return _arr;
			},
			_getColumn: function($row, columnName, selected){
				var that = this;
					$column = $row.find('input[name={0}]'.format(columnName));

				if($column.length > 0){
					if(selected){
						if($column.prop('checked')){
							return $column;
						}
						return null;
					}
					return $column;
				}

				return null;
			}
		};

		var action = arguments[0],
			method = methods[action];
		if(method){
			arguments = Array.prototype.slice.call(arguments, 1);
		}else if(isObjectType(action) || !action){
			method = methods['init'];
		}else{
			$.error('method ' + action + ' does not exists on jQuery.BambooDataTable');
		}
		var ret = method.apply(this, arguments);
		if('getOption' == action || 'getSelectedRows' == action || 'selectedAllRows' == action){
			//供外部调用
			return ret;
		}
		return this;
	};

	$.fn.BambooDataTable.defaultOptions = {
		id: '',//元素ID
		width: '',//宽度
		height: '',//高度
		url: '',//数据请求链接
		queryParamObj: {},//查询参数对象
		dataType: 'json',//请求数据类型
		data: null,//表格数据
		isPaginaion: true,//是否分页
		paginationAlign: 'right',
		currentPage: 1,//当前页
		pageSize: 10,//每页条数
		currentPageFieldName: 'page',//当前页字段名
		pageSizeFieldName: 'pageSize',//每页条数字段名
		visiblePage: 6,//可见页
		getTotal: function(ret){
			return ret['total'];
		},//获取总条数的回调函数
		getData: function(ret){
			return ret['data'];
		},//获取数据列表的回调函数
		delegateEvenHref: null,//超链接委托事件
		initRender: false, //初始化渲染
		columns:[],//列信息，有field、title、width、align、render
		toolbar: [],//工具栏，有text、name、handler
		isTreeData: false,//是否是树形数据
		getTreeColumnName: function(){
			return 'children'
		}//树形表格数据所在的列名称
	};
})(jQuery);


/**
 * 变量类型判断
 * @Author   qingyezhu
 * @DateTime 2016-10-14T14:35:48+0800
 * @param    {[type]}                 param 变量
 * @param    {[type]}                 type  具体某种类型
 * @return   {[type]}                       true/false
 */
function typeJudge(param, type){
	return Object.prototype.toString.call(param) === '[object {0}]'.format(type);
}
/**
 * 是否是数字类型
 * @Author   qingyezhu
 * @DateTime 2016-10-14T14:35:41+0800
 * @param    {[type]}                 param [description]
 * @return   {Boolean}                      [description]
 */
function isNumberType(param){
	return typeJudge(param, 'Number');
}
/**
 * 是否是字符串类型
 * @Author   qingyezhu
 * @DateTime 2016-10-14T14:35:34+0800
 * @param    {[type]}                 param [description]
 * @return   {Boolean}                      [description]
 */
function isStringType(param){
	return typeJudge(param, 'String');
}
/**
 * 是否是布尔类型
 * @Author   qingyezhu
 * @DateTime 2016-10-14T14:35:25+0800
 * @param    {[type]}                 param 变量
 * @return   {Boolean}                      true/false
 */
function isBooleanType(param){
	return typeJudge(param, 'Boolean');
}
/**
 * 是否是数组类型
 * @Author   qingyezhu
 * @DateTime 2016-10-14T14:35:17+0800
 * @param    {[type]}                 param 变量
 * @return   {Boolean}                      true/false
 */
function isArrayType(param){
	return typeJudge(param, 'Array');
}
/**
 * 是否是对象类型
 * @Author   qingyezhu
 * @DateTime 2016-10-14T14:35:06+0800
 * @param    {[type]}                 param 变量
 * @return   {Boolean}                      true/false
 */
function isObjectType(param){
	return typeJudge(param, 'Object');
}
/**
 * 是否是函数类型
 * @Author   qingyezhu
 * @DateTime 2016-10-14T14:34:57+0800
 * @param    {[type]}                 param 变量
 * @return   {Boolean}                      true/false
 */
function isFunctionType(param){
	return typeJudge(param, 'Function');
}
/**
 * 变量是否是null
 * @Author   qingyezhu
 * @DateTime 2016-10-14T14:34:14+0800
 * @param    {[type]}                 param 变量
 * @return   {Boolean}                      true/false
 */
function isNullType(param){
	return typeJudge(param, 'Null');
}
/**
 * 变量是否是undefined
 * @Author   qingyezhu
 * @DateTime 2016-10-14T14:23:44+0800
 * @param    {[type]}                 param 变量
 * @return   {Boolean}                      true/false
 */
function isUndefined(param){
	return typeJudge(param, 'Undefined');
}
	
/**
 * 是否为null或undefined
 * @param obj
 * @returns {Boolean}
 */
function isNull(obj){
	return obj == null || obj == undefined;
}

/**
*JQuery的Ajax请求模版
*/
function ajaxJQuery(_options){
	if(!isObjectType(_options)){
		$.error('参数类型不正确，应为对象类型');
	}

	var url = _options['url'];
	if(!url){
		$.error('没有请求链接');
	}

	var options = {
		url: url,
		method: _options['method'] || 'post',
		dataType: _options['dataType'] || 'json',
		data: _options['data'] || {},
		cache: _options['cache'] || false,
		success: function(data){
			var successCallBack = _options['callback'];
			successCallBack && successCallBack(data);
		},
		error: function(){
			var errorCallBack = _options['errorCallback'];
			if(errorCallBack){
				errorCallBack();
			}else{
				showMsg('网络或服务器异常，请稍后重试');
			}
		}
	};
	$.ajax(options);
}


/**
* 创建遮罩层
* @param maskId
* @param zIndex
*/
function createMask(maskId, zIndex){
	return $('#{0}'.format(maskId)).BambooMaskLayer({
		'zIndex': zIndex || 9999
	});
}

/**
 * 打开遮罩层
 * @param msg
 * @param zIndex
 */
function openMask(msg, zIndex){
	var _$maskLayer = window['$maskLayer'];
	if(_$maskLayer){
		_$maskLayer.BambooMaskLayer('openMask', msg, zIndex);
		//页面滚动条滚动到顶部
		$('body').animate({scrollTop: 0});
	}
}

/**
 * 关闭遮罩层
 */
function closeMask(){
	var _$maskLayer = window['$maskLayer'];
	if(_$maskLayer){
		_$maskLayer.BambooMaskLayer('closeMask');
	}
}


/**
* 创建提示框
* @param tipMsgId
*/
function createTipMsg(tipMsgId){
	return $('#{0}'.format(tipMsgId)).BambooTipMessage()
}

/**
* 展示提示信息
* @param text
*/
function showMsg(text){
	var _$tipMessage = window['$tipMessage'];
	if(_$tipMessage){
		showTipMsg(_$tipMessage, text);
	 }
}

/**
 * 展示消息提示框
 */
function showTipMsg($tipMessage, msg){
	$tipMessage.BambooTipMessage('openTipMessage', msg);
	setTimeout(function(){
		$tipMessage.BambooTipMessage('closeTipMessage');
	}, 2000);
}



/**
 * 初始化开关按钮
 * @param $elem 开关按钮的父节点div
 * @param name
 */
function initSwitchBtn($elem, name){
	$elem.on('click', 'a', function(){
		if(this.className == 'bamboo-switch-btn'){
			var $switchBtn = $form.find('input[name={0}]'.format(name));
			var _value = $switchBtn.val();
			var $span = $(this).find('span');
			var _text = $span.text();
			var _data = $span.attr('data');
			if(_value == 1){
				//有效到无效
				_value = 0;
				$span.addClass('bamboo-switch-close-btn').removeClass('bamboo-switch-open-btn');
				this.style.backgroundColor = '#ff0000';
			}else{
				//无效到有效
				_value = 1;
				$span.addClass('bamboo-switch-open-btn').removeClass('bamboo-switch-close-btn');
				this.style.backgroundColor = '#0aa284';
			}
			$span.text(_data);
			$span.attr('data', _text);
			$switchBtn.val(_value);
			return false;					
		}
	});
}


/**
 * 更改开关按钮按钮
 * @param $elem 开关按钮的父节点
 * @param name 开关值的组件名
 * @param value 开关值
 */
function changeSwitch($parant, name, value){
	var _arr = ['关', '开'];
	var _val =  (value + 1) % 2;
	$parant.find('input[name={0}]'.format(name)).val(_val);
	$parant.find('.bamboo-switch-btn span').attr('data', _arr[value]).text(_arr[_val]);
	$parant.find('.bamboo-switch-btn').trigger('click');
}


/**
 * 获取下拉框Html代码<br/>
 * flag：文本是否是value-text，默认不是<br/>
 * attr：是否有共享的数据<br/>
 * effect：是否需要有效的元素<br/>
 * @Author   qingyezhu
 * @DateTime 2016-10-13T15:00:09+0800
 * @param    {[type]}                 arr 数据源，格式：[{'value': value, 'text': text, 'data': data, 'effect': 1/0}]
 * @param    {[type]}                 obj 条件参数，格式：{'flag': true/false, 'attr': 'data', 'effect': true/false}
 * @return   {[type]}                     下拉框Html代码
 */
function _getSelectHtml(arr, obj){
	var _htmlArr = [],
		 flag = null,
		 attr = null,
		 effectMark = null,
		_item = null,
		_value = null,
		_text = null,
		_effect = null,
		_attr = null,
		_optionHtml = null;
	if(!isNull(obj)){
		flag = obj['flag'];
		attr = obj['attr'];
		effectMark = obj['effect'];
	}
	for(var _i = 0, _len = arr.length;_i < _len; _i++){
		_item = arr[_i];
		_value = _item['value'];
		_text = _item['text'];
		if(effectMark){
			_effect = _item['effect'];
			if(_effect == 0){
				continue;
			}
		}
		if(flag && _value != -1){
			_text = _value + '--' + _text;
		}
		if(attr){
			_attr = _item[attr];
			_optionHtml = '<option value=\"{0}\" data=\"{1}\">{2}</option>'.format(_value, _attr ? _attr : '', _text);
		}else{
			_optionHtml = '<option value=\"{0}\">{1}</option>'.format(_value, _text);
		}
		_htmlArr.push(_optionHtml);
	}
	return _htmlArr.join('');
}

/**
 * 将对象转换成下拉框所需要的数据源<br/>
 * 数组格式：[{'value': value, 'text': text, 'data': data }]<br/>
 * 对象格式：{value:{'text': text, 'data': data}}<br/>
 * @Author   qingyezhu
 * @DateTime 2016-10-13T14:50:42+0800
 * @param    {[type]}                 obj      数据对象，如数组，或对象
 * @param    {[type]}                 paramArr 条件参数数组，如['text', 'data']
 * @return   {[type]}                          数据源
 */
function objToArrForSelect(obj, paramArr){
	var arr = [];
	$.each(obj, function(value, text){
		var _item = {
			'value': value,
			'text': text
		};
		if(isObjectType(text)){
			$.each(paramArr, function(index, param){
				var _val = text[param];
				if(!isNull(_val)){
					_item[param] = _val; 
				}
			});
		}
		arr.push(_item);
	});
	return arr;
}

/**
 * 获取下拉框的Html代码
 * @Author   qingyezhu
 * @DateTime 2016-10-13T14:47:12+0800
 * @param    {[type]}                 object   数据对象
 * @param    {[type]}                 paramObj 参数对象
 * @return   {[type]}                          下拉框Html代码
 */
function getSelectHtml(object, paramObj){
	var arr = object;
	if(isObjectType(object)){
		arr = objToArrForSelect(object, ['text', 'data']);
	}
	return _getSelectHtml(arr, paramObj);
}


var BambooMapObj = function(){
	this._entrys = new Array();
	
	/**
	 * 获取Map的长度
	 */
	this.getLength = function(){
		return this._entrys.length;
	};
	
	/**
	 * 添加：当有存在的相同的key时，value总是最新的
	 */
	this.put = function(key, value){
		if(isNull(key)){
			return ;
		}
		var _index = _getIndexOf.call(this, key);
		if(_index == -1){
			//添加
			var _entry = {
					'key': key,
					'value': value
			};
			this._entrys.push(_entry);
		}else{
			//覆盖之前
			this._entrys[_index] = value;
		}
	};
	
	/**
	 * 根据key获取对应的value
	 */
	this.get = function(key){
		var _index = _getIndexOf.call(this, key);
		return (_index != -1) ? this._entrys[_index]['value'] : null;
	};
	
	/**
	 * 根据key删除
	 */
	this.remove = function(key){
		var _index = _getIndexOf.call(this, key);
		if(_index != -1){
			this._entrys.splice(_index, 1);
			return true;
		}
		return false;
	}
	
	/**
	 * 是否包含
	 */
	this.constains = function(key){
		var _index = _getIndexOf.call(this, key);
		return (_index != -1) ? true : false;
	}
	
	/**
	 * 根据key查看其中数组中的位置
	 * @param key
	 * @returns {Number}
	 */
	function _getIndexOf(key){
		if(isNull(key)){
			return -1;
		}
		var _length = this._entrys.length;
		for(var i = 0;i < _length;i ++){
			var _entry = this._entrys[i];
			if(isNull(_entry)){
				continue ;
			}
			if(_entry.key == key){
				return i;
			}
		}
		return -1;
	};
}