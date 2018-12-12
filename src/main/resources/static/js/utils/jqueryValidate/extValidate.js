/**
 * 扩展正则规则
 */
//配置通用的默认提示语
$.extend($.validator.messages, {
    required: '必填',
    equalTo: "请再次输入相同的值"
});

//验证只能是中英文2-20个字符
jQuery.validator.addMethod("checkStrZy", function (value, element) {
    var rex = /^[\u0391-\uFFE5A-Za-z]{2,20}$/;
    return this.optional(element) || (rex.test(value));
}, "只能输入中文或英文,长度2-20位");

//验证只能是中英文数字1-32个字符
jQuery.validator.addMethod("checkStrZys", function (value, element) {
    var rex = /^[\u0391-\uFFE5a-zA-Z0-9]{1,32}$/;
    return this.optional(element) || (rex.test(value));
}, "只能输入中文或英文或数字,长度1-32位");

//只能输入英文2-20个字符
jQuery.validator.addMethod("isENS", function (value, element) {
    var rex = /^[a-zA-Z]{2,20}$/;
    return this.optional(element) || (rex.test(value));
}, "只能输入英文,长度2-20位");


//只能输入英文数字和//,长度2-50位
jQuery.validator.addMethod("isENSXieGang", function (value, element) {
    var rex = /^[a-zA-Z0-9\/]{2,50}$/;
    return this.optional(element) || (rex.test(value));
}, "只能输入英文数字和//,长度2-50位");


//只能输入数字0-9,,长度1-10位c
jQuery.validator.addMethod("isNumber", function (value, element) {
    var rex = /^[0-9]\d{0,10}$/;
    return this.optional(element) || (rex.test(value));
}, "只能输入数字,长度1-10位");


//只能输入非零的正整数,,长度1-10位
jQuery.validator.addMethod("isNumberNew", function (value, element) {
    var rex = /^\+?[1-9][0-9]*$/;
    return this.optional(element) || (rex.test(value));
}, "只能输入非零的正整数,长度1-10位");


jQuery.validator.addMethod("isMobile", function (value, element) {
    var rex = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
    return this.optional(element) || (rex.test(value));
}, "手机号码格式不正确");

//只能输入非零的正整数,,长度1-10位
jQuery.validator.addMethod("isMoneyNumber", function (value, element) {
    var rex = /^(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/;
    return this.optional(element) || (rex.test(value));
}, "只能输入正数");

//验证至少N位数字
jQuery.validator.addMethod("lastNumber", function (value, element) {
    var rex = /^\d{1,}$/;
    return this.optional(element) || (rex.test(value));
}, "只允许输入数字!");

//验证身份证
jQuery.validator.addMethod("isIdCard", function (value, element) {
    var rex = /^[0-9a-zA-Z]+$/;
    return this.optional(element) || (rex.test(value));
}, "身份证号码格式不正确!");
