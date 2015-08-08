/**
 * Created by artem on 08.08.15.
 */

define(['angular',
        'src/main/assets/common/filters/filters',
        'src/main/assets/common/services/playRoutes',
        'angularMocks'],
    function () {

        describe('myFilter', function () {

            // Here we register the function returned by the filer AMD module
           // `common.filters` - is the name of module in 'src/main/assets/common/filters/filters'
            beforeEach(module('common.filters'));

            it('should not be null', inject(function ($filter) {
                expect($filter("myFilter")(2)).toEqual([0, 1, 2]);
            }));

        });
    }
);