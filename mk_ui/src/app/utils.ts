import { Injectable } from '@angular/core';
import { DatePipe } from '@angular/common';
import { saveAs } from 'file-saver';
import { WorkSheet, WorkBook, write, utils } from 'xlsx';
import { Router } from '@angular/router';

const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
const EXCEL_EXTENSION = '.xlsx';

/**
 * Utilities
 */
@Injectable()
export class Utils {
    constructor(private rou: Router) { }

    /**
     * To ISO string with timezone offset
     * @param s
     */
    public toISOString(s: any): String {
        let d = new Date(s);
        /*let o = d.getTimezoneOffset() * 60000;
        return new Date((d.getTime() - o)).toISOString();*/
        return d.toISOString();
    }

    public exportToExcel(json: any[], fileName: string): void {
        const worksheet: WorkSheet = utils.json_to_sheet(json);
        const workbook: WorkBook = { Sheets: { 'Data': worksheet }, SheetNames: ['Data'] };
        const excelBuffer: any = write(workbook, { bookType: 'xlsx', type: 'buffer' });
        this.saveFile(excelBuffer, fileName);
    }

    private saveFile(buffer: any, fileName: string): void {
        const data: Blob = new Blob([buffer], { type: EXCEL_TYPE });
        saveAs(data, fileName + EXCEL_EXTENSION);
    }

    public getName(name) {
        let d = new Date();
        return name + this.formatDate(d, 'yyyyMMddHHmmss');
    }

    public formatDate(d: Date, format: string) {
        let res = "";

        if (d != null) {
            let datePipe = new DatePipe('en-us');
            res = datePipe.transform(d, format);
        }

        return res;
    }
}