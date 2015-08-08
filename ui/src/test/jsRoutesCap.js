/**
 * Created by artem on 08.08.15.
 */

/**
 * Cap if jsRoutes for correct testing angular models
 */
var jsRoutes = {}; (function(_root){
    var _nS = function(c,f,b){var e=c.split(f||"."),g=b||_root,d,a;for(d=0,a=e.length;d<a;d++){g=g[e[d]]=g[e[d]]||{}}return g}
    var _qS = function(items){var qs = ''; for(var i=0;i<items.length;i++) {if(items[i]) qs += (qs ? '&' : '') + items[i]}; return qs ? ('?' + qs) : ''}
    var _s = function(p,s){return p+((s===true||(s&&s.secure))?'s':'')+'://'}
    var _wA = function(r){return {ajax:function(c){c=c||{};c.url=r.url;c.type=r.method;return jQuery.ajax(c)}, method:r.method,type:r.method,url:r.url,absoluteURL: function(s){return _s('http',s)+'localhost:9000'+r.url},webSocketURL: function(s){return _s('ws',s)+'localhost:9000'+r.url}}}

    _nS('controllers.Application'); _root.controllers.Application.index =
        function() {
            return _wA({method:"GET", url:"/"})
        }

})(jsRoutes)
