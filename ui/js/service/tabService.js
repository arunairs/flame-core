/**
 * Created by anczhang on 8/22/16.
 */
app.factory("tabService", function ($timeout, $animateCss) {

    var service = {};

    service.selectTab = function (tab, index, opts) {
        var bmTabs = tab.closest('bm-tabs');
        var panel = bmTabs.children('bm-tabs-panel');
        var tabs = panel.children('md-tab-content');
        var selectedIndex = panel.children('md-tab-content.bm-active').index();
        if (selectedIndex == index)return;
        console.log(tabs.eq(selectedIndex)[0].offsetHeight + " " + tabs.eq(index)[0].offsetHeight);
        if (!opts || !opts.skipAnimate) {
            $animateCss(panel, {
                from: {'height': tabs.eq(selectedIndex)[0].offsetHeight + 5 + 'px'},
                to: {'height': tabs.eq(index)[0].offsetHeight + 5 + 'px'},
                easing: 'cubic-bezier(0.35, 0, 0.25, 1)',
                duration: 0.5
            }).start().done(function () {
                panel.css({height: 'auto'});
            });

            if (selectedIndex < index) {
                tabs.eq(selectedIndex).removeClass('bm-active').removeClass('bm-right').addClass('bm-left');
                tabs.eq(index).addClass('no-transition').addClass('bm-right').removeClass('bm-left');
                $timeout(function () {
                    tabs.eq(index).removeClass('no-transition').addClass('bm-active');
                }, 0)
            } else if (selectedIndex > index) {
                tabs.eq(selectedIndex).removeClass('bm-active').removeClass('bm-left').addClass('bm-right');
                tabs.eq(index).addClass('no-transition').removeClass('bm-right').addClass('bm-left');
                $timeout(function () {
                    tabs.eq(index).removeClass('no-transition').addClass('bm-active');
                }, 0)
            }
        } else if (opts.skipAnimate) {
            if (selectedIndex < index) {
                tabs.eq(selectedIndex).addClass('no-transition').removeClass('bm-active').removeClass('bm-right').addClass('bm-left');
                tabs.eq(index).addClass('no-transition').removeClass('bm-right').removeClass('bm-left').addClass('bm-active');
                $timeout(function () {
                    tabs.eq(selectedIndex).removeClass('no-transition');
                    tabs.eq(index).removeClass('no-transition');
                }, 0)
            } else if (selectedIndex > index) {
                tabs.eq(selectedIndex).addClass('no-transition').removeClass('bm-active').removeClass('bm-left').addClass('bm-right');
                tabs.eq(index).addClass('no-transition').removeClass('bm-right').removeClass('bm-left').addClass('bm-active');
                $timeout(function () {
                    tabs.eq(selectedIndex).removeClass('no-transition');
                    tabs.eq(index).removeClass('no-transition');
                }, 0)
            }
        }
    };

    return service;
})