import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'truncate',
  standalone: true,
})
export class TruncatePipe implements PipeTransform {
  transform(
    value: string,
    limit: number,
    completeWords: boolean = false,
    ellipsis: string = '...'
  ): string {
    if (completeWords) {
      limit = value.substring(0, limit).lastIndexOf(' ');
    }
    return `${value.substring(0, limit)}${
      value.length > limit ? ellipsis : ''
    }`;
  }
}
