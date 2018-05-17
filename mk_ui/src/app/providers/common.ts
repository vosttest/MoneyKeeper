import 'rxjs/add/operator/toPromise';
import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class CommonProvider {
    constructor(private api: ApiProvider) { }

    /**
     * Search by
     * @param type
     */
    public search(type: string) {
        let x = {
            'keyword': type
        };

        return this.api.post('common/search', x);
    }

    /**
     * Get images by
     * @param type
     */
    public getImages(type: string) {
        let x = {
            'type': 'Image',
            'value': type
        };
        return this.api.post('common/search-image', x);
    }
}