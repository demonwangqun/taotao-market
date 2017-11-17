var TTCart = {
	load : function(){ // 鍔犺浇璐墿杞︽暟鎹�
		
	},
	itemNumChange : function(){
		$(".increment").click(function(){//锛�
			var _thisInput = $(this).siblings("input");
			_thisInput.val(eval(_thisInput.val()) + 1);
			$.post("/cart/add/"+_thisInput.attr("itemId")+ ".html?num=1",function(data){
				TTCart.refreshTotalPrice();
			});
		});
		$(".decrement").click(function(){//-
			var _thisInput = $(this).siblings("input");
			if(eval(_thisInput.val()) == 1){
				return ;
			}
			_thisInput.val(eval(_thisInput.val()) - 1);
			$.post("/cart/add/"+_thisInput.attr("itemId")+ ".html?num=-1",function(data){
				TTCart.refreshTotalPrice();
			});
		});
		$(".quantity-form .quantity-text").rnumber(1);//闄愬埗鍙兘杈撳叆鏁板瓧
		$(".quantity-form .quantity-text").change(function(){
			var _thisInput = $(this);
			$.post("/service/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val(),function(data){
				TTCart.refreshTotalPrice();
			});
		});
	},
	refreshTotalPrice : function(){ //閲嶆柊璁＄畻鎬讳环
		var total = 0;
		$(".quantity-form .quantity-text").each(function(i,e){
			var _this = $(e);
			total += (eval(_this.attr("itemPrice")) * 10000 * eval(_this.val())) / 10000;
		});
		$(".totalSkuPrice").html(new Number(total/100).toFixed(2)).priceFormat({ //浠锋牸鏍煎紡鍖栨彃浠�
			 prefix: '￥',
			 thousandsSeparator: ',',
			 centsLimit: 2
		});
	}
};

$(function(){
	TTCart.load();
	TTCart.itemNumChange();
});