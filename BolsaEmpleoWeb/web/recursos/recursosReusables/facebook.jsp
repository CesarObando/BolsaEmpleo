<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

        <div id="fb-root"></div>
        <script>(function (d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id))
                    return;
                js = d.createElement(s);
                js.id = id;
                js.src = "//connect.facebook.net/es_LA/sdk.js#xfbml=1&version=v2.0";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));
        </script>

        <script type="text/javascript" src="../js/jquery.js"></script>
        <script type="text/javascript" src="../js/hoverIntent.js"></script>
        <script type="text/javascript" src="../js/superfish.js"></script>                
        <script type="text/javascript" src="../js/supersubs.js"></script>

        <script type="text/javascript">
            // initialise plugins
            jQuery(function () {
                jQuery('ul.sf-menu').superfish();
            });
        </script>


    </body>
</html>
