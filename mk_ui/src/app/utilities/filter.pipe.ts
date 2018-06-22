import { Pipe, PipeTransform } from '@angular/core';
@Pipe({
    name: 'filter'
})
export class FilterPipe implements PipeTransform {
    transform(items: any[], searchText: string): any[] {
        if (!items) return [];
        if (searchText) {
            return items.filter(item => item.text.toLocaleLowerCase().indexOf(searchText.toLocaleLowerCase()) > -1);
        }
        else {
            return items;
        }
    }
}
@Pipe({
    name: 'filterDashBoard'
})
export class FilterPipeDashBoard implements PipeTransform {
    transform(items: any[], searchText: string): any[] {
        if (!items) return [];
        if (searchText) {
            return items.filter(item => item.text.toLocaleLowerCase().indexOf(searchText.toLocaleLowerCase()) > -1);
        }
        else {
            return items;
        }
    }
}
@Pipe({
    name: 'filterSetting'
})
export class FilterPipeSetting implements PipeTransform {
    transform(items: any[], searchText: string): any[] {
        if (!items) return [];
        if (searchText) {
            return items.filter(item => item.text.toLocaleLowerCase().indexOf(searchText.toLocaleLowerCase()) > -1);
        }
        else {
            return items;
        }
    }
}
@Pipe({
    name: 'filterSignUp'
})
export class FilterPipeSingUp implements PipeTransform {
    transform(items: any[], searchText: string): any[] {
        if (!items) return [];
        if (searchText) {
            return items.filter(item => item.text.toLocaleLowerCase().indexOf(searchText.toLocaleLowerCase()) > -1);
        }
        else {
            return items;
        }
    }
}