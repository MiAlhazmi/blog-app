import { Component, Input } from '@angular/core';
import { IconDefinition } from '@fortawesome/free-solid-svg-icons';

@Component({
	selector: 'app-sidebaricon',
	template: `
    <div class="sidebar-icon group">
      <div>
        <fa-icon [icon]="icon"></fa-icon>
      </div>
      <span class="sidebar-tooltip group-hover:scale-100">
        {{ text }}
      </span>
    </div>
  `,
	styles: [`
	.sidebar-icon {
		/* position: relative; */
		display: flex;
		outline: none;
		align-items: center;
		justify-content: center;
		height: 3rem;
		width: 3vw;
		max-width: 3rem;
		margin-top: 0.5rem;
		margin-bottom: 0.5rem;
		margin-left: auto;
		margin-right: auto;
		background-color: var(--color-surface-800);
		color: var(--color-signal);
		cursor: pointer;
		transition-property: background-color, color, border-radius, box-shadow;
		transition-duration: 0.3s;
		transition-timing-function: linear;
		border-radius: 0.9rem;
		box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
	}

	.sidebar-icon:hover {
		background-color: var(--color-signal);
		color: var(--color-dark);
		border-radius: 0,9rem;
		--tw-scale-x: 0.5;
		--tw-scale-y: 0.5;
		transform: translate(var(--tw-translate-x),
				var(--tw-translate-y)) rotate(var(--tw-rotate)) skewX(var(--tw-skew-x)) skewY(var(--tw-skew-y)) scaleX(var(--tw-scale-x)) scaleY(var(--tw-scale-y));
	}

	/* .sidebar-icon:hover .sidebar-tooltip {
		@apply scale-100;
	} */

	.sidebar-tooltip {
		position: absolute;
		width: auto;
		padding: 0.5rem;
		margin: 0.5rem;
		min-width: max-content;
		left: 3.5rem;
		border-radius: 0.375rem;
		box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);
		color: #ffffff;
		background-color: #1f2937;
		font-size: 0.75rem;
		font-weight: bold;
		transition-property: transform;
		transition-duration: 0.1s;
		transform: scale(0);
		transform-origin: left;
	}

	.sidebar-icon:hover .sidebar-tooltip {
		transform: scale(1);
	}

	/* .sidebar-hr {
		background-color: #e5e7eb;
		border: 1px solid #e5e7eb;
		border-color: #e5e7eb;
		border-radius: 9999px;
		margin-left: 0.5rem;
		margin-right: 0.5rem;
	} */
`],
})

export class SidebariconComponent {
	@Input() icon!: IconDefinition;
	@Input() text: string = '';
}
