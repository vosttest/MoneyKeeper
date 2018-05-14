import 'rxjs/add/operator/toPromise';
import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class AccountProvider {
    constructor(private api: ApiProvider) { }

    /**
     * Search by
     * @param type
     */
    public search() {
        let x = {};
        return this.api.post('account/search', x);
    }
}