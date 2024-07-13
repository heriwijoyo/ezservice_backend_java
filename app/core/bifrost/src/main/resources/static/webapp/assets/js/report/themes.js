var themes = (function() {
    var containers = null;
    var pickerEditor = {};
    var root = document.documentElement;

    // Current properties
    var current = {
        jexcel_header_color: '#000',
        jexcel_header_color_highlighted: '#000',
        jexcel_header_background: '#f3f3f3',
        jexcel_header_background_highlighted: '#dcdcdc',
        jexcel_content_color: '#000',
        jexcel_content_color_highlighted: '#000',
        jexcel_content_background: '#fff',
        jexcel_content_background_highlighted: 'rgba(0,0,0,0.05)',
        jexcel_menu_background: '#fff',
        jexcel_menu_background_highlighted: '#ebebeb',
        jexcel_menu_color: '#555',
        jexcel_menu_color_highlighted: '#555',
        jexcel_menu_box_shadow: '2px 2px 2px 0px rgba(143, 144, 145, 1)',
        jexcel_border_color: '#ccc',
        jexcel_border_color_highlighted: '#000',
        active_color: '#007aff',
    }

    // Available themes
    var available = {
        jexcel: {
            jexcel_header_color: '#000',
            jexcel_header_color_highlighted: '#000',
            jexcel_header_background: '#f3f3f3',
            jexcel_header_background_highlighted: '#dcdcdc',
            jexcel_content_color: '#000',
            jexcel_content_color_highlighted: '#000',
            jexcel_content_background: '#fff',
            jexcel_content_background_highlighted: 'rgba(0,0,0,0.05)',
            jexcel_menu_background: '#fff',
            jexcel_menu_background_highlighted: '#ebebeb',
            jexcel_menu_color: '#555',
            jexcel_menu_color_highlighted: '#555',
            jexcel_menu_box_shadow: '2px 2px 2px 0px rgba(143, 144, 145, 1)',
            jexcel_border_color: '#ccc',
            jexcel_border_color_highlighted: '#000',
            active_color: '#007aff',
        },
        dark: {
            jexcel_header_color: '#888',
            jexcel_header_color_highlighted: '#444',
            jexcel_header_background: '#313131',
            jexcel_header_background_highlighted: '#777',
            jexcel_content_color: '#777',
            jexcel_content_color_highlighted: '#333',
            jexcel_content_background: '#3e3e3e',
            jexcel_content_background_highlighted: '#333',
            jexcel_menu_background: '#7e7e7e',
            jexcel_menu_background_highlighted: '#ebebeb',
            jexcel_menu_color: '#ddd',
            jexcel_menu_color_highlighted: '#222',
            jexcel_menu_box_shadow: 'unset',
            jexcel_border_color: '#5f5f5f',
            jexcel_border_color_highlighted: '#999',
            active_color: '#eee',
        }
    }

    var obj = function() {
        var options = {
            minDimensions:[10,10],
            license: '39130-64ebc-bd98e-26bc4'
        }

        jexcel(document.getElementById('spreadsheet'), options);

        var containers = document.querySelectorAll('.colorpicker');
        for (var i = 0; i < containers.length; i++) {
            jSuites.color(containers[i].children[0], {
                onchange: function(a, b) {
                    // Update color
                    a.style.backgroundColor = b;
                    // Update property
                    obj.updateProperty(a.id, b);
                },
                closeOnChange: true,
            });
        }

        obj.updateConfig();
    }

    obj.updateProperty = function(k, v) {
        // Update property in the table
        root.style.setProperty('--' + k, v);
        // Update current property
        current[k] = v;
        // Reload configuration
        obj.updateConfig();
    }

    obj.load = function(o) {
        // Get new values
        if (typeof(o) == 'object') {
            var o = o.getAttribute('data-theme');
        }
        current = available[o];

        // Update config
        obj.updateConfig();
    }

    obj.updateConfig = function() {
        var keys = Object.keys(current);
        var style = '';
        for (var i = 0; i < keys.length; i++) {
            root.style.setProperty('--' + keys[i], current[keys[i]]);
            if (document.getElementById(keys[i])) {
                document.getElementById(keys[i]).style.backgroundColor = current[keys[i]];
            }
            style += '    --' + keys[i] + ': ' + current[keys[i]] + ';<br>';
        }
        document.getElementById('style').innerHTML = '&#60;style><br>:root {<br>' + style + '}<br>&#60;/style>';
    }

    return obj;
})();