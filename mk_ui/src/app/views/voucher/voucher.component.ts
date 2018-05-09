import { Component, OnInit } from '@angular/core';
import { TreeviewItem } from 'ngx-treeview';

@Component({
    selector: 'app-voucher',
    templateUrl: './voucher.component.html',
    styleUrls: ['./voucher.component.css']
})
export class VoucherComponent implements OnInit {

    config = {
        'hasAllCheckBox': false,
        'hasFilter': true,
        'hasCollapseExpand': false,
        'decoupleChildFromParent': false,
        'maxHeight': 500
    }

    items: TreeviewItem[];

    constructor() { }

    ngOnInit() {
        this.testing();
    }

    public testing() {
        let l1: any[] = [];
        let l2: any[] = [];
        let l3: any[] = [];
    }
}