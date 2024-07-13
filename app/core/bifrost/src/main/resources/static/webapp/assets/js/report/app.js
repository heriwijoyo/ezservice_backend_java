var app = (function() {
    // Create menu
    if (document.getElementById('menu')) {
        menu.build(document.getElementById('menu'));
    }

    // Render tabs
    var tabs = document.querySelectorAll('.tabs');
    for (var i = 0; i < tabs.length; i++) {
        jSuites.tabs(tabs[i], {
            animation: true,
        });
    }

    // Render modules
    var modules = document.querySelectorAll('[data-autoload]');
    for (var i = 0; i < modules.length; i++) {
        var m = modules[i].getAttribute('data-autoload');
        var f = jSuites.path.call(this, m);
        if (typeof(f) == 'function') {
            lemonade.apply(modules[i], f(modules[i]));
        }
    }

    // Loader
    var script = document.querySelector('script[type="text/loader"]');
    if (script) {
        eval(script.innerText)
    }

    document.querySelectorAll('.prettyprint').forEach((el) => {
        hljs.highlightElement(el);
        if (el.classList.contains('linenums')) {
            hljs.lineNumbersBlock(el);
        }
    });
});

var menu = {};

menu.build = function(root) {
    var self = {};

    self.create = function() {
        me.t.create(null, null, function(result) {
            if (result.success) {
                window.location.href = '/me/t/' + result.token;
            } else {
                jSuites.notification(result);
            }
        });
    }

    // Jsuites menu
    jSuites.menu(root);

    // Global reference
    menu.element = self;

    // Run lemonade
    lemonade.apply(root, self);
}