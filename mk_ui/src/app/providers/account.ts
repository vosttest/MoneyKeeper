import 'rxjs/add/operator/toPromise';
import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class AccountProvider {
    constructor(private api: ApiProvider) { }

    /**
     * Save
     */
    public save(vm) {
        return this.api.post('account/save', vm);
    }

    /**
     * Search by
     * @param type
     */
    public search(obj:any) {
        return this.api.post('account/search', obj);
    }
}