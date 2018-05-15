import { Injectable } from '@angular/core';
import 'rxjs/add/operator/toPromise';
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
    public search(info: any) {
        return this.api.post('account/search', info);
    }

    /**
     * Get Account by Id
     *
     */
    public getAccount(id: any) {
        return this.api.get('account/getById/' + id);
    }

    /**
     * Delete by Id
     */
    public delete(id: any) {
        return this.api.delete('account/delete/' + id);
    }
}