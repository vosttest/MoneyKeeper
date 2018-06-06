import 'rxjs/add/operator/toPromise';
import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class VoucherProvider {
    constructor(private api: ApiProvider) { }

    /**
     * Save
     */
    public save(vm) {
        return this.api.post('voucher/save', vm);
    }

    /**
     * Delete
     */
    public delete(id: any) {
        return this.api.delete('voucher/delete/' + id);
    }

    /**
     * Search
     */
    public search(info: any) {
        return this.api.post('voucher/search', info);
    }

    public getVoucher(id: any) {
        return this.api.get('voucher/view/' + id);
    }
}