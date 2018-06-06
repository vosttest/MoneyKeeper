import 'rxjs/add/operator/toPromise';
import { Injectable } from '@angular/core';
import { ApiProvider } from './api';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class SettingProvider {
    constructor(private api: ApiProvider) { }

    /**
     * Search by
     * @param type
     */
    public search() {
        let x = {};
        return this.api.post('setting/search', x);
    }

    /**
     * Save setting
     * @param info
     */
    public save(info: any, isFirst: boolean = false) {
        let x = {
            id: info.id,
            value: info.value,
            status: info.status,
            isFirst: isFirst
        };
        return this.api.post('setting/save', info);
    }

    /**
     * View setting
     * @param code
     */
    public view(code: string) {
        let x = { "keyword": code };
        return this.api.post('setting/view', x);
    }

    /**
     * Get exchange rate
     */
    public exrate() {
        return this.api.get('setting/exrate');
    }

    /**
     * Get JSON language file
     * @param file
     */
    public getLang(file: string): Observable<any> {
        return this.api.getx("./assets/dat/" + file);
    }
}