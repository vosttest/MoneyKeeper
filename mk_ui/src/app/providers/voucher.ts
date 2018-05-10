import 'rxjs/add/operator/toPromise';
import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class VoucherProvider {
    constructor(private api: ApiProvider) { }

    /**
     * Search
     */
    public save(vm) {
        return this.api.post('voucher/save', vm);
    }

    public delete(id: number) {
        return this.api.delete('voucher/delete?id=' + id);
    }
}