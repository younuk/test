"use strict";

/**
 * @name 콘솔버그막기
 * @description 콘솔객체가 없을경우 에뮬레이션이 아닌 실제 인터넷 익스플로러9이하에서 콘솔로그 버그를 막을 수 있습니다. 막지 않고 콘솔을 쓸경우 모든 스크립팅은 중단 됩니다. 대체콘솔은 console.comment에 담겨있습니다.
 * @since 2017-01-16
 */
if(!this.console) {
	this.console = {
		methods : ["assert",
				   "clear",
				   "count",
				   "debug",
				   "dir",
				   "dirxml",
				   "error",
				   "exception",
				   "group",
				   "groupCollapsed",
				   "groupEnd",
				   "info",
				   "log",
				   "markTimeline",
				   "profile",
				   "profileEnd",
				   "table",
				   "time",
				   "timeEnd",
				   "timeStamp",
				   "trace",
				   "warn"],
		methodsIndex : 0,
		comment : []
	};

	for(console.methodsIndex in console.methods) {
		if(!console[console.methods[console.methodsIndex]]) {
			console[console.methods[console.methodsIndex]] = function(comment) {
				this.comment.push(comment);
			};
		}
	}

	this.console.flag = false;
}else{
	this.console.flag = true;
}