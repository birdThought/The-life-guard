

calculate = {}

/**
 * 计算字符串长度
 * @param string
 * @returns {Number}
 */
calculate.strLength = function(string){
	var length = 0;
	for (var i = 0; i < string.length; i++) {
		var c = string.charCodeAt(i);
		if (calculate.isSingleByteCharacter) {
			length++;
		} else {
			length += 2;
		}
	}
	return length;
}

calculate.inputByteLengthAndReturnIndexOfString = function(byteLength, string){
	var nowIndex = 0;
	var nowByteLength = 0;
	
	for(var i = 0; i < string.length; i++){
		if(calculate.isSingleByteCharacter(string.charCodeAt(i))){
			nowByteLength ++;
		}else{
			nowByteLength += 2;
		}
		nowIndex ++;
		if(byteLength <= nowByteLength){
			break;
		}
	}
	return nowIndex;
}

calculate.isSingleByteCharacter = function(character){
	if ((character >= 0x0001 && character <= 0x007E) || (0xFF60 <= character && character <= 0XFF9F)) {
		return true;
	} else {
		return false;
	}
}