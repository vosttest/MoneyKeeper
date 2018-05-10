import 'rxjs/add/operator/toPromise';
import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

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
}