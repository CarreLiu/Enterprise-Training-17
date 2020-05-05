/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	/**
     * 这里使用CKEditor 4.8 Full版本
     */
    config.toolbar = 'Full';
    config.height = 242;
    config.resize_enabled = false;           	//禁止拖拽改变尺寸
    config.removePlugins = 'elementspath';     	//删除底边栏
    config.enterMode = CKEDITOR.ENTER_BR;		//屏蔽换行符<br>
	config.shiftEnterMode = CKEDITOR.ENTER_P;	//屏蔽段落<p>
    //添加中文字体
    config.font_names="宋体/SimSun;新宋体/NSimSun;仿宋_GB2312/FangSong_GB2312;楷体_GB2312/KaiTi_GB2312;黑体/SimHei;微软雅黑/Microsoft YaHei;幼圆/YouYuan;华文彩云/STCaiyun;华文行楷/STXingkai;方正舒体/FZShuTi;方正姚体/FZYaoti;"+ config.font_names;
    
    config.toolbar_Full = [
        ['Font','FontSize'],
        ['Bold','Italic','Underline'],
        ['TextColor','BGColor'],
        ['JustifyLeft','JustifyCenter','JustifyRight'],
        ['NumberedList','BulletedList'],
        ['Smiley'],
        // ['Link','Unlink'],
        // ['Image'],
        // ['Image','Table','SpecialChar']
        // ['Replace'],
        // ['Maximize'],
        // ['Source'],
    ];

};
